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
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.TRAXCountryCodes;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * A helper class to convert internal TRAX country codes to/from ISO 3166
 * alpha-2 (the standard used by Java).
 *
 * @author bryce.bates
 */
@PermitAll
//@DeclareRoles({TRAX_READ})
public class CountryConverterService implements CountryConverter, Serializable {

    private static final long serialVersionUID = 2L;

    private static final String CLASSNAME = CountryConverterService.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private Map<String, String> isoTraxMap;
    private Map<String, String> traxIsoMap;
    private Map<String, String> isoCountryMap;
    private Map<String, String> traxCountryMap;

    @PersistenceContext
    private transient EntityManager em;

    private synchronized void initialize() {

        final TypedQuery<TRAXCountryCodes> query
                = getEntityManager().createNamedQuery("TRAXCountryCodes.getAll", TRAXCountryCodes.class);
        List<TRAXCountryCodes> resultList = query.getResultList();

        isoTraxMap = new HashMap<>();
        traxIsoMap = new HashMap<>();
        isoCountryMap = new HashMap<>();
        traxCountryMap = new HashMap<>();
        for (TRAXCountryCodes code : resultList) {
            final String countryName = code.getCountryName() == null ? "" : code.getCountryName().trim();
            final String isoCode = code.getISOCountryCode() == null ? "" : code.getISOCountryCode().trim();
            final String traxCode = code.getTRAXCountryCode() == null ? "" : code.getTRAXCountryCode().trim();
            isoTraxMap.put(isoCode, traxCode);
            isoCountryMap.put(countryName, isoCode);
            traxIsoMap.put(traxCode, isoCode);
            traxCountryMap.put(countryName, traxCode);
        }
    }

    /**
     * Should only be used for unit tests.
     *
     * @return
     */
    @Override
    public Map<String, String> getISOTRAXMap() {
        if (isoTraxMap == null) {
            initialize();
        }
        return isoTraxMap;
    }

    @Override
    public Map<String, String> getISOCountryMap() {
        if (isoCountryMap == null) {
            initialize();
        }
        return isoCountryMap;
    }

    @Override
    public Map<String, String> getTRAXCountryMap() {
        if (traxCountryMap == null) {
            initialize();
        }
        return traxCountryMap;
    }

    /**
     * Should only be used for unit tests.
     *
     * @return
     */
    @Override
    public Map<String, String> getTRAXISOMap() {
        if (traxIsoMap == null) {
            initialize();
        }
        return traxIsoMap;
    }

    /**
     * Convert from the passed in ISO 3166 Alpha-2 country code to the TRAX
     * country code.
     *
     * @param isoCountryCode
     * @return
     */
    @Override
    public String isoToTRAX(final String isoCountryCode) {
        return getISOTRAXMap().get(isoCountryCode);
    }

    /*
     * Convert from the passed in TRAX
     * country code to the ISO 3166 Alpha-2 country code .

     * @param traxCountryCode
     * @return isoCountryCode
     */
    @Override
    public String traxToISO(final String traxCountryCode) {
        return getTRAXISOMap().get(traxCountryCode);
    }

    private EntityManager getEntityManager() {
        return this.em;
    }
}
