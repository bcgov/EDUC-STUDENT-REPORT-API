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
 *  File:                $Id:: TswTranNongradEntityPK.java 9614 2018-03-12 18:#$
 *  Date of Last Commit: $Date:: 2018-03-12 11:06:47 -0700 (Mon, 12 Mar 2018)  $
 *  Revision Number:     $Rev:: 9614                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Represents a composite key for TswTranNongradEntity instances.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Embeddable
public class TswTranNongradEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 10)
    @Column(name = "STUD_NO", insertable = false, updatable = false)
    private String studNo;
    @Size(max = 12)
    @Column(name = "NON_GRAD_CODE", insertable = false, updatable = false)
    private String nonGradCode;

    public TswTranNongradEntityPK() {
    }

    public String getStudNo() {
        return this.studNo;
    }

    public String getNonGradCode() {
        return this.nonGradCode;
    }
}
