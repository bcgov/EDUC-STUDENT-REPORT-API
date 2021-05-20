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

import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.*;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.CourseId;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An auto generated entity for the TSW_TRAN_COURSE_VW view which is a view
 * created in the ISD database and linked to the TRAX database tsw_tran_crse
 * table using the database link. An embedded composite primary key has been
 * added since this data does not have a unique primary key column. The view
 * definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE VIEW "ISD"."TSW_TRAN_COURSE_VW" ("STUD_NO",
 * "COURSE_NAME", "CRSE_CODE", "CRSE_LEVEL", "COURSE_SESSION", "NUM_CREDITS",
 * "EXAM_PCT", "SCHOOL_PCT", "FINAL_PCT", "FINAL_LG", "INTERIM_MARK",
 * "FOUNDATION_REQ", "SPECIAL_CASE", "UPDATE_DT", "RPT_CRS_TYPE") AS ( SELECT
 * stud_no, course_name, crse_code, crse_level, course_session, num_credits,
 * exam_pct, school_pct, final_pct, final_lg, interim_mark, foundation_req,
 * special_case, update_dt, rpt_crs_type FROM tsw_tran_crse@traxlink.world )
 * with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the Transcript, XML Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Entity
@Table(name = ENTITY_STUDENT_TRANSCRIPT_RESULTS)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TswTranCourseEntity.findByKey", query = "SELECT s FROM TswTranCourseEntity s WHERE s.primaryKey = :primaryKey"),
    @NamedQuery(name = "TswTranCourseEntity.findMaxUpdtByStudNo", query = "SELECT max(s.updateDt) FROM TswTranCourseEntity s WHERE s.primaryKey.studNo = :studNo")})
public class TswTranCourseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "crseSession", column = @Column(name = COL_COURSE_PK_SESSION))
    })
    private CourseId primaryKey;
    @Size(max = 40)
    @Column(name = COL_COURSE_NAME, insertable = false, updatable = false)
    private String courseName;
    @Size(max = 3)
    @Column(name = COL_COURSE_CREDITS, insertable = false, updatable = false)
    private String numCredits;
    @Size(max = 3)
    @Column(name = COL_COURSE_EXAM_PERCENT, insertable = false, updatable = false)
    private String examPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_SCHOOL_PERCENT, insertable = false, updatable = false)
    private String schoolPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_FINAL_PERCENT, insertable = false, updatable = false)
    private String finalPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_FINAL_LETTER_GRADE, insertable = false, updatable = false)
    private String finalLg;
    @Size(max = 3)
    @Column(name = COL_COURSE_INTERIM_MARK, insertable = false, updatable = false)
    private String interimMark;
    @Size(max = 2)
    @Column(name = COL_TRANSCRIPT_FOUNDATION_REQUIREMENT, insertable = false, updatable = false)
    private String foundationReq;
    @Size(max = 2)
    @Column(name = COL_TRANSCRIPT_SPECIAL_CASE, insertable = false, updatable = false)
    private String specialCase;
    @Column(name = COL_AUDIT_UPDATED_DATE, insertable = false, updatable = false)
    private Long updateDt;
    @Column(name = COL_TRANSCRIPT_REPORT_COURSE_TYPE, insertable = false, updatable = false)
    private Character rptCrsType;

    public TswTranCourseEntity() {
    }

    /**
     * get the entity primary key. It is a compound key of type CourseId.
     *
     * @return entity primary key object
     */
    public CourseId getPrimaryKey() {
        return primaryKey;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getNumCredits() {
        return numCredits;
    }

    public String getExamPct() {
        return examPct;
    }

    public String getSchoolPct() {
        return schoolPct;
    }

    public String getFinalPct() {
        return finalPct;
    }

    public String getFinalLg() {
        return finalLg;
    }

    public String getInterimMark() {
        return interimMark;
    }

    public String getFoundationReq() {
        return foundationReq;
    }

    public String getSpecialCase() {
        return specialCase;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public Character getRptCrsType() {
        return rptCrsType;
    }
}
