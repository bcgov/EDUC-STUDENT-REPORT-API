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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * An auto generated entity for the TSW_MAILER_DEMOG_VW which is a view created
 * in the ISD database and linked to the TRAX database tsw_mailer_demog table
 * using the database link. The view definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE VIEW "ISD"."TSW_MAILER_DEMOG_VW" ("STUD_NO",
 * "FIRST_NAME", "LAST_NAME", "UPDATE_DT") AS (select stud_no, first_name,
 * last_name, update_dt from tsw_mailer_demog@traxlink.world) with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the ExamResults service.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Entity
@Table(name = "TSW_MAILER_DEMOG_VW")
public class TswMailerDemogEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = "STUD_NO", insertable = false, updatable = false)
    @Id
    private String studNo;
    @Size(max = 40)
    @Column(name = "FIRST_NAME", insertable = false, updatable = false)
    private String firstName;
    @Size(max = 40)
    @Column(name = "LAST_NAME", insertable = false, updatable = false)
    private String lastName;
    @Column(name = "UPDATE_DT", insertable = false, updatable = false)
    private Long updateDt;
    @Column(name = "BIRTHDATE", insertable = false, updatable = false)
    private Long birthdate;

    public TswMailerDemogEntity() {
    }

    public String getStudNo() {
        return studNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public Long getBirthdate() {
        return birthdate;
    }
}
