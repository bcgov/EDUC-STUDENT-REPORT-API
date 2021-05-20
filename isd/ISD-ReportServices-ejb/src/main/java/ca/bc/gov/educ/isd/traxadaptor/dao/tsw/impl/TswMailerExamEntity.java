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
 *  File:                TswMailerExamEntity.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * An auto generated entity for the TSW_MAILER_EXAM_VW which is a view created
 * in the ISD database and linked to the TRAX database tsw_mailer_exam table
 * using the database link. An embedded composite primary key has been added
 * since this data does not have a unique primary key column. The view
 * definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE VIEW "ISD"."TSW_MAILER_EXAM_VW" ("STUD_NO",
 * "CRSE_CODE", "CRSE_LEVEL", "COURSE_NAME", "COURSE_SESSION", "SCHOOL_PCT",
 * "BEST_SCHOOL_PCT", "EXAM_PCT", "BEST_EXAM_PCT", "FINAL_PCT", "FINAL_LG") AS
 * (select stud_no, crse_code, crse_level, course_name, course_session,
 * school_pct, best_school_pct, exam_pct, best_exam_pct, final_pct, final_lg
 * from tsw_mailer_exam@traxlink.world) with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the ExamResults service.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
@Entity
@Table(name = ENTITY_STUDENT_EXAM)
public class TswMailerExamEntity implements Serializable {

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
    @Column(name = COL_COURSE_SCHOOL_PERCENT, insertable = false, updatable = false)
    private String schoolPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_BEST_SCHOOL_PERCENT, insertable = false, updatable = false)
    private String bestSchoolPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_EXAM_PERCENT, insertable = false, updatable = false)
    private String examPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_BEST_EXAM_PERCENT, insertable = false, updatable = false)
    private String bestExamPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_FINAL_PERCENT, insertable = false, updatable = false)
    private String finalPct;
    @Size(max = 3)
    @Column(name = COL_COURSE_FINAL_LETTER_GRADE, insertable = false, updatable = false)
    private String finalLg;

    public TswMailerExamEntity() {
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

    public String getSchoolPct() {
        return schoolPct;
    }

    public String getBestSchoolPct() {
        return bestSchoolPct;
    }

    public String getExamPct() {
        return examPct;
    }

    public String getBestExamPct() {
        return bestExamPct;
    }

    public String getFinalPct() {
        return finalPct;
    }

    public String getFinalLg() {
        return finalLg;
    }
}
