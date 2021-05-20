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

import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.*;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * An auto generated entity for the TSW_TRAN_DEMOG_VW view which is a view
 * created in the ISD database and linked to the TRAX database tsw_tran_demog
 * table using the database link. JPA access is on attributes directly. The
 * attributes in this entity are used to fulfill data requests for the
 * Transcript, XML Transcript services, and Exam services.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Entity
@Table(name = ENTITY_STUDENT_TRANSCRIPT_DEMOGRAPHICS)
@NamedQueries({
    @NamedQuery(name = "TswTranDemogEntity.findByStudNo", query = "SELECT s FROM TswTranDemogEntity s WHERE s.studNo = :studNo")})
public class TswTranDemogEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = COL_STUDENT_NUMBER, insertable = false, updatable = false)
    @Id
    private String studNo;
    @Size(max = 40)
    @Column(name = COL_STUDENT_DEMOG_NAME_FIRST, insertable = false, updatable = false)
    private String firstName;
    @Size(max = 40)
    @Column(name = COL_STUDENT_DEMOG_NAME_MIDDLE, insertable = false, updatable = false)
    private String middleName;
    @Size(max = 40)
    @Column(name = COL_STUDENT_DEMOG_NAME_LAST, insertable = false, updatable = false)
    private String lastName;
    @Column(name = COL_STUDENT_DEMOG_BIRTHDATE, insertable = false, updatable = false)
    private Long birthdate;
    @Size(max = 12)
    @Column(name = "LOCAL_ID", insertable = false, updatable = false)
    private String localId;
    @Column(name = COL_STUDENT_GENDER, insertable = false, updatable = false)
    private Character studGender;
    @Size(max = 8)
    @Column(name = COL_MINCODE, insertable = false, updatable = false)
    private String mincode;
    @Size(max = 2)
    @Column(name = COL_STUDENT_GRADE, insertable = false, updatable = false)
    private String studGrade;
    @Size(max = 8)
    @Column(name = COL_GRADUATION_DATE, insertable = false, updatable = false)
    private String gradDate;
    @Size(max = 4)
    @Column(name = COL_GRADUATION_REQUIREMENT_YEAR, insertable = false, updatable = false)
    private String gradReqtYear;
    @Column(name = COL_AUDIT_UPDATED_DATE, insertable = false, updatable = false)
    private Long updateDt;
    @Size(max = 2)
    @Column(name = COL_SCHOOL_LOGO, insertable = false, updatable = false)
    private String logoType;
    @Size(max = 1024)
    @Column(name = COL_GRADUATION_MESSAGE, insertable = false, updatable = false)
    private String gradMsgTxt;
    @Size(max = 1)
    @Column(name = COL_GRADUATION_FLAG, insertable = false, updatable = false)
    private Character gradFlag;
    @Size(max = 1)
    @Column(name = COL_STUDENT_DEMOG_STATUS, insertable = false, updatable = false)
    private Character currentFormerFlag;

    public TswTranDemogEntity() {
    }

    public String getStudNo() {
        return studNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public String getLocalId() {
        return localId;
    }

    public Character getStudGender() {
        return studGender;
    }

    public String getMincode() {
        return mincode;
    }

    public String getStudGrade() {
        return studGrade;
    }

    public String getGradDate() {
        return gradDate;
    }

    public String getGradReqtYear() {
        return gradReqtYear;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public String getLogoType() {
        return logoType;
    }

    public String getGradMsg() {
        return gradMsgTxt;
    }

    public Character getGradFlag() {
        return gradFlag;
    }

    public Character getCurrentFormerFlag() {
        return currentFormerFlag;
    }
}
