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
package ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An auto generated entity for the TSW_TRAN_NONGRAD_VW which is a view created
 * in the ISD database and linked to the TRAX database tsw_tran_nongrad table
 * using the database link. JPA access is on attributes directly. The attributes
 * in this entity are used to fulfill data requests for the Transcript, XML
 * Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */

@XmlRootElement
public class TswTranNongradEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TswTranNongradEntityPK id;

    private String nonGradDesc;
    private Long updateDt;

    public TswTranNongradEntity() {
    }

    public TswTranNongradEntityPK getId() {
        return this.id;
    }

    public String getStudNo() {
        return getId().getStudNo();
    }

    public String getNonGradCode() {
        return getId().getNonGradCode();
    }

    public String getNonGradDesc() {
        return this.nonGradDesc;
    }

    public Long getUpdateDt() {
        return this.updateDt;
    }
}
