/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                TswPSIRegistryDataBean.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import ca.bc.gov.educ.isd.traxadaptor.impl.TRAXCountryConverter;
import ca.bc.gov.educ.isd.eis.EISException;
import static ca.bc.gov.educ.isd.eis.roles.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;
import ca.bc.gov.educ.isd.eis.trax.db.TSWRegistry;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswPSIRegistryEntity;
import ca.bc.gov.educ.isd.traxadaptor.utils.ExceptionUtilities;
import ca.bc.gov.educ.isd.traxadaptor.service.TswPSIRegistryData;
import ca.bc.gov.educ.isd.traxadaptor.impl.TSWRegistryImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. The EJB provides
 * methods to support the retrieval, insertion and update in PSI Registry table
 * that exist in TRAX database.
 *
 * @author CGI Information Management Consultants Inc.
 */
@DeclareRoles({TRAX_READ, FULFILLMENT_SERVICES_USER})
public class TswPSIRegistryDataBean implements TswPSIRegistryData, Serializable {

    // ------------------ CONSTANT(S) AND FINAL(S)
    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = TswPSIRegistryDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String findRegistryByQuery
            = "SELECT "
            + "new " + TSWRegistryImpl.class.getCanonicalName() + "("
            + "i.psiCode, "
            + "i.psiName, "
            + "i.psiCslCode, "
            + "i.psiAttnName, "
            + "i.address1, "
            + "i.address2, "
            + "i.address3, "
            + "i.city, "
            + "i.provCode, "
            + "i.traxCountryCode, "
            + "i.openFlag, "
            + "i.globalSignOff, "
            + "i.psiPostal, "
            + "i.fax, "
            + "i.phone1, "
            + "i.transmissionMode, "
            + "i.psisCode, "
            + "i.psiUrl, "
            + "i.psiGrouping) "
            + "FROM TswPSIRegistryEntity i "
            + "WHERE i.psiCode = ?1 ";

    // ------------------ VARIABLE(S)
    @PersistenceContext
    private transient EntityManager em;

    // ------------------ CONSTRUCTOR(S)
    // ------------------ GETTER(S) AND SETTER(S)
    // ------------------ METHOD(S)
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findRegistryBy(final String psiCode) throws EISException {
        final String _m = "findChoiceBy(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TSWRegistry> query = em.createQuery(findRegistryByQuery, TSWRegistry.class);
        query.setParameter(1, psiCode);

        List<TSWRegistry> tswPSIRegistry = query.getResultList();

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findPSIs() throws EISException {
        final String _m = "findPSIs()";
        LOG.entering(CLASSNAME, _m);

        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TswPSIRegistryEntity> query = em.createNamedQuery("TswPSIRegistryEntity.findAll", TswPSIRegistryEntity.class);

        List<TswPSIRegistryEntity> psiRegistry = query.getResultList();

        List<TSWRegistry> tswPSIRegistry = translatedFrom(psiRegistry);

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findBCPSIs() throws EISException {
        final String _m = "findBCPSIs()";
        LOG.entering(CLASSNAME, _m);

        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TswPSIRegistryEntity> query = em.createNamedQuery("TswPSIRegistryEntity.findByProvCode", TswPSIRegistryEntity.class);
        query.setParameter("provCode", "BC");

        List<TswPSIRegistryEntity> psiRegistry = query.getResultList();

        List<TSWRegistry> tswPSIRegistry = translatedFrom(psiRegistry);

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findPSIsByName(String name) throws EISException {
        final String _m = "findPSIsByName(String)";
        LOG.entering(CLASSNAME, _m);

        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TswPSIRegistryEntity> query = em.createNamedQuery("TswPSIRegistryEntity.findByPsiNameLike", TswPSIRegistryEntity.class);
        query.setParameter("psiName", "%".concat(name).concat("%"));

        List<TswPSIRegistryEntity> psiRegistry = query.getResultList();

        List<TSWRegistry> tswPSIRegistry = translatedFrom(psiRegistry);

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findNonBCCAPSIs() throws EISException {
        final String _m = "findNonBCCAPSIs()";
        LOG.entering(CLASSNAME, _m);

        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TswPSIRegistryEntity> query = em.createNamedQuery("TswPSIRegistryEntity.findByProvCodeNEAndCntryCode", TswPSIRegistryEntity.class);
        query.setParameter("provCode", "BC");
        query.setParameter("cntryCode", TRAXCountryConverter.getInstance().isoToTRAX("CA"));

        List<TswPSIRegistryEntity> psiRegistry = query.getResultList();

        List<TSWRegistry> tswPSIRegistry = translatedFrom(psiRegistry);

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<TSWRegistry> findNonCAPSIs() throws EISException {
        final String _m = "findNonCAPSIs()";
        LOG.entering(CLASSNAME, _m);

        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        final TypedQuery<TswPSIRegistryEntity> query = em.createNamedQuery("TswPSIRegistryEntity.findByCntryCodeNE", TswPSIRegistryEntity.class);
        query.setParameter("cntryCode", TRAXCountryConverter.getInstance().isoToTRAX("CA"));

        LOG.info("***** about to start getResultList.");
        List<TswPSIRegistryEntity> psiRegistry = query.getResultList();
        LOG.info("***** done getResultList.");

        List<TSWRegistry> tswPSIRegistry = translatedFrom(psiRegistry);
        LOG.info("***** done translatedFrom.");

        LOG.exiting(CLASSNAME, _m);
        return tswPSIRegistry;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public void insertNewRegistry(final TSWRegistry newPSIResgistry) throws EISException {
        final String _m = "insertNewRegistry(TSWRegistry)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (newPSIResgistry == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Registry to create parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        TswPSIRegistryEntity registryToInsert = translateFrom(newPSIResgistry);
        em.persist(registryToInsert);
        LOG.log(Level.FINE, "Object identified by PSI Code = {0} was inserted.", registryToInsert.getPsiCode());

        LOG.exiting(CLASSNAME, _m);
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public Boolean updateRegistry(final String psiCode, final TSWRegistry registryDataToUpdate) throws EISException {
        final String _m = "updateRegistry(String, TSWRegistry)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            } else if (registryDataToUpdate == null) {
                ex = ExceptionUtilities.setupRuntimeException("The Registry data to be update parameter should not be null.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean response = false;

        TswPSIRegistryEntity registryToUpdate = translateFrom(registryDataToUpdate);
        TswPSIRegistryEntity psiRegistryToUpdate = em.find(TswPSIRegistryEntity.class, psiCode);

        if (psiRegistryToUpdate != null) {
            LOG.log(Level.FINE, "Object identified by PSI Code = {0} was found.", registryToUpdate.getPsiCode());

            psiRegistryToUpdate.setPsiName(registryToUpdate.getPsiName());
            psiRegistryToUpdate.setPsiCslCode(registryToUpdate.getPsiCslCode());
            psiRegistryToUpdate.setPsiAttnName(registryToUpdate.getPsiAttnName());
            psiRegistryToUpdate.setAddress1(registryToUpdate.getAddress1());
            psiRegistryToUpdate.setAddress2(registryToUpdate.getAddress2());
            psiRegistryToUpdate.setAddress3(registryToUpdate.getAddress3());
            psiRegistryToUpdate.setCity(registryToUpdate.getCity());
            psiRegistryToUpdate.setProvCode(registryToUpdate.getProvCode());
            psiRegistryToUpdate.setTRAXCountryCode(registryToUpdate.getTRAXCountryCode());
            psiRegistryToUpdate.setOpenFlag(registryToUpdate.getOpenFlag());
            psiRegistryToUpdate.setGlobalSignOff(registryToUpdate.getGlobalSignOff());
            psiRegistryToUpdate.setPsiPostal(registryToUpdate.getPsiPostal());
            psiRegistryToUpdate.setFax(registryToUpdate.getFax());
            psiRegistryToUpdate.setPhone1(registryToUpdate.getPhone1());
            psiRegistryToUpdate.setTransmissionMode(registryToUpdate.getTransmissionMode());
            psiRegistryToUpdate.setPsisCode(registryToUpdate.getPsisCode());
            psiRegistryToUpdate.setPsiUrl(registryToUpdate.getPsiUrl());
            psiRegistryToUpdate.setPsiGrouping(registryToUpdate.getPsiGrouping());
            response = true;

            LOG.log(Level.FINE, "Object identified by PSI Code = {0} was updated.", registryToUpdate.getPsiCode());
        } else {
            LOG.log(Level.WARNING, "Object identified by PSI Code = {0} was not found.", registryToUpdate.getPsiCode());
        }

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public Boolean deleteRegistry(final String psiCode) throws EISException {
        final String _m = "deleteRegistry(String)";
        LOG.entering(CLASSNAME, _m);

        //<editor-fold desc="Verify input and pre-conditions.">
        {
            RuntimeException ex = null;
            if (psiCode == null) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be null.");
            } else if (psiCode.isEmpty()) {
                ex = ExceptionUtilities.setupRuntimeException("The PSI Code parameter should not be empty.");
            }

            ExceptionUtilities.parameterValidationException(ex, _m);
        }
        //</editor-fold>
        LOG.log(Level.FINE, ExceptionUtilities.LOG_FINE_VALIDATION_DONE);

        boolean response = false;

        TswPSIRegistryEntity psiRegistryToDelete = em.find(TswPSIRegistryEntity.class, psiCode);

        if (psiRegistryToDelete != null) {
            LOG.log(Level.FINE, "Object identified by PSI Code = {0} was found.", psiCode);

            em.remove(psiRegistryToDelete);
            response = true;

            LOG.log(Level.FINE, "Object identified by PSI Code = {0} was deleted.", psiCode);
        } else {
            LOG.log(Level.WARNING, "Object identified by PSI Code = {0} was not found.", psiCode);
        }

        LOG.exiting(CLASSNAME, _m);
        return response;
    }

    // ------------------
    /**
     * Translates a TSWRegistry object into a TswPSIRegistryEntity object.
     *
     * @param newPSIResgistry TSWRegistry object to be translated into a
     * TswPSIRegistryEntity
     * @return an object that represents a PSI Registry Entity.
     */
    private TswPSIRegistryEntity translateFrom(final TSWRegistry newPSIResgistry) {
        final String _m = "translateFrom(TSWRegistry)";
        LOG.entering(CLASSNAME, _m);
        TswPSIRegistryEntity newRegistry = new TswPSIRegistryEntity();

        newRegistry.setPsiCode(newPSIResgistry.getPsiCode());
        newRegistry.setPsiName(newPSIResgistry.getPsiName());
        newRegistry.setPsiCslCode(newPSIResgistry.getPsiCslCode());
        newRegistry.setPsiAttnName(newPSIResgistry.getPsiAttnName());
        newRegistry.setAddress1(newPSIResgistry.getAddress1());
        newRegistry.setAddress2(newPSIResgistry.getAddress2());
        newRegistry.setAddress3(newPSIResgistry.getAddress3());
        newRegistry.setCity(newPSIResgistry.getCity());
        newRegistry.setProvCode(newPSIResgistry.getProvCode());
        newRegistry.setTRAXCountryCode(newPSIResgistry.getTRAXCountryCode());
        newRegistry.setOpenFlag(newPSIResgistry.getOpenFlag());
        newRegistry.setGlobalSignOff(newPSIResgistry.getGlobalSignOff());
        newRegistry.setPsiPostal(newPSIResgistry.getPsiPostal());
        newRegistry.setFax(newPSIResgistry.getFax());
        newRegistry.setPhone1(newPSIResgistry.getPhone1());
        newRegistry.setTransmissionMode(newPSIResgistry.getTransmissionMode());
        newRegistry.setPsisCode(newPSIResgistry.getPsisCode());
        newRegistry.setPsiUrl(newPSIResgistry.getPsiUrl());
        newRegistry.setPsiGrouping(newPSIResgistry.getPsiGrouping());

        LOG.exiting(CLASSNAME, _m);
        return newRegistry;
    }

    private TSWRegistry translateFrom(final TswPSIRegistryEntity newPSIResgistry) {
        final String _m = "translateFrom(TswPSIRegistryEntity)";
        LOG.entering(CLASSNAME, _m);
        TSWRegistry newRegistry = new TSWRegistryImpl(
                newPSIResgistry.getPsiCode(),
                newPSIResgistry.getPsiName(),
                newPSIResgistry.getPsiCslCode(),
                newPSIResgistry.getPsiAttnName(),
                newPSIResgistry.getAddress1(),
                newPSIResgistry.getAddress2(),
                newPSIResgistry.getAddress3(),
                newPSIResgistry.getCity(),
                newPSIResgistry.getProvCode(),
                newPSIResgistry.getTRAXCountryCode(), newPSIResgistry.getOpenFlag(),
                newPSIResgistry.getGlobalSignOff(),
                newPSIResgistry.getPsiPostal(),
                newPSIResgistry.getFax(),
                newPSIResgistry.getPhone1(),
                newPSIResgistry.getTransmissionMode(),
                newPSIResgistry.getPsisCode(),
                newPSIResgistry.getPsiUrl(),
                newPSIResgistry.getPsiGrouping());

        LOG.exiting(CLASSNAME, _m);
        return newRegistry;
    }

    private List<TSWRegistry> translatedFrom(List<TswPSIRegistryEntity> psiRegistryList) {
        final String _m = "translateFrom(List<TswPSIRegistryEntity>)";
        LOG.entering(CLASSNAME, _m);
        List<TSWRegistry> newRegistryList = new ArrayList<>();

        for (TswPSIRegistryEntity registryToTranslate : psiRegistryList) {
            TSWRegistry translatedRegistry = translateFrom(registryToTranslate);
            newRegistryList.add(translatedRegistry);
        }

        LOG.exiting(CLASSNAME, _m);
        return newRegistryList;
    }

}
