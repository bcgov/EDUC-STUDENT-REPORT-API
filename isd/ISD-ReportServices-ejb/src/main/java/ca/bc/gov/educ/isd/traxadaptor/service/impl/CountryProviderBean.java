/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.isd.eis.trax.db.CountryConverter;
import ca.bc.gov.educ.isd.eis.trax.db.CountryProvider;
import ca.bc.gov.educ.isd.traxadaptor.impl.TRAXCountryConverter;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.trax.Constants.DATETIME_YYYY_MM_DD_HH_MM_SS;
import static ca.bc.gov.educ.isd.traxadaptor.impl.TRAXCountryConverter.*;

/**
 * This Bean is started when the application begins and creates/refreshes the
 * properties files containing the TRAX mappings.
 */
public class CountryProviderBean implements CountryProvider {

    private static final long serialVersionUID = 2L;

    private static final String CLASSNAME = CountryProviderBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String UPDATED_DATE = "updatedDate";

    private long intervalSeconds = 60L * 60L * 24L * 7L;

    private Properties isoToTrax;
    private Properties traxToIso;

    CountryConverter countryConverter;

    @PostConstruct
    public void init() {
        isoToTrax = new Properties();
        traxToIso = new Properties();
        loadPropertiesFiles();
        checkExpiredAndProcess();
    }

    /**
     * Check if the data has expired and if so reset.
     */
    @Override
    public boolean checkExpiredAndProcess() {
        boolean expired = false;
        if (hasExpired(traxToIso) || hasExpired(isoToTrax)) {
            reset();
            expired = true;
        }
        return expired;
    }

    /**
     * Update the existing files to new values.
     */
    @Override
    public void reset() {
        updatePropertiesFiles();

        writePropertiesFiles();

        // Finally, force the converter to reread the new values next time it runs.
        TRAXCountryConverter.getInstance().clear();
    }

    /**
     * Was this properties file last updated before intervalSeconds ago?
     *
     * @param props
     * @return
     */
    private boolean hasExpired(final Properties props) {
        boolean expired = true;
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_YYYY_MM_DD_HH_MM_SS);
        if (props != null) {
            String updatedDate = props.getProperty(UPDATED_DATE);
            if (updatedDate != null) {
                try {
                    Date updated = sdf.parse(updatedDate);
                    Date compare = new Date();
                    compare.setTime(compare.getTime() - intervalSeconds * 1000L);
                    if (updated.after(compare)) {
                        expired = false;
                    }
                } catch (ParseException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }

        return expired;
    }

    @Override
    public long getIntervalSeconds() {
        return intervalSeconds;
    }

    @Override
    public void setIntervalSeconds(final long interSeconds) {
        this.intervalSeconds = interSeconds;
    }

    private void loadPropertiesFiles() {
        loadPropertiesFile(traxToIso, TRAX_ISO_PROPERTIES_NAME);
        loadPropertiesFile(isoToTrax, ISO_TRAX_PROPERTIES_NAME);
    }

    private void writePropertiesFiles() {
        writePropertiesFile(traxToIso, TRAX_ISO_PROPERTIES_NAME);
        writePropertiesFile(isoToTrax, ISO_TRAX_PROPERTIES_NAME);
    }

    private void updatePropertiesFiles() {
        updatePropertiesFile(traxToIso, countryConverter.getTRAXISOMap());
        updatePropertiesFile(isoToTrax, countryConverter.getISOTRAXMap());
    }

    private void updatePropertiesFile(Properties props, final Map<String, String> map) {
        // read through
        if (map == null || map.isEmpty()) {
            LOG.log(Level.WARNING, "Map to update is empty - review TRAX country code table.");
        } else {
            props.clear();
            props.putAll(map);
        }
    }

    private void writePropertiesFile(final Properties props, final String name) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_YYYY_MM_DD_HH_MM_SS);
        String updateDate = sdf.format(new Date());
        props.setProperty(UPDATED_DATE, updateDate);

        File file = new File(System.getProperty(SYSTEM_PROPERTY_DIR), name);
        LOG.log(Level.FINER, "Writing properties to: {0}.", file.getAbsoluteFile());
        try (OutputStream mapStream = new FileOutputStream(file)) {
            props.store(mapStream, "");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void loadPropertiesFile(final Properties props, final String name) {
        File file = new File(System.getProperty(SYSTEM_PROPERTY_DIR), name);

        if (file.exists()) {
            try (InputStream mapStream = new FileInputStream(file)) {
                LOG.log(Level.FINER, "Loading properties from: {0}.", file.getAbsoluteFile());
                props.load(mapStream);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }

        if (props.isEmpty()) {
            try {
                Enumeration<URL> resources = getClass().getClassLoader().getResources(META_INF + name);
                while (resources.hasMoreElements()) {
                    try (InputStream mapStream = resources.nextElement().openStream()) {
                        LOG.log(Level.FINER, "Loading properties from resource: {0}.", name);
                        props.load(mapStream);
                    } catch (IOException ex) {
                        LOG.log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

}
