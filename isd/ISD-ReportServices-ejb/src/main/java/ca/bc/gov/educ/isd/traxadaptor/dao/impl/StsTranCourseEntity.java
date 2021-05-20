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
package ca.bc.gov.educ.isd.traxadaptor.dao.impl;

import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.*;
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
 * An auto generated entity for the STS_TRAN_CRSE_VW view which is a view
 * created in the ISD database and linked to the TRAX database STS_TRAN_CRSE
 * table using the database link. An embedded composite primary key has been
 * added since this data does not have a unique primary key column. The view
 * definition is as follows:
 * <p>
 * CREATE OR REPLACE FORCE EDITIONABLE VIEW ISD.STS_TRAN_CRSE_VW AS SELECT *
 * FROM STS_TRAN_CRSE@TRAXLINK.WORLD; with read only;
 * </p><br>
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the Transcript, XML Transcript services.
 *
 * @author CGI Information Management Consultants Inc.
 */
@XmlRootElement
public class StsTranCourseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private CourseId primaryKey;
    private String courseName;
    private String numCredits;
    private String examPct;
    private String schoolPct;
    private String finalPct;
    private String finalLg;
    private String interimMark;
    private String interimLetterGrade;
    private String foundationReq;
    private String specialCase;
    private Long updateDt;
    private Character rptCrsType;
    private Character crsType;
    private String relatedCrse;
    private String relatedLevel;
    private Character usedForGrad;

    public StsTranCourseEntity() {
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

    public String getInterimLetterGrade() {
        return interimLetterGrade;
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

    public Character getCrsType() {
        return crsType;
    }

    public Character getUsedForGrad() {
        return usedForGrad;
    }

    public String getRelatedCrse() {
        return relatedCrse;
    }

    public String getRelatedLevel() {
        return relatedLevel;
    }

}
