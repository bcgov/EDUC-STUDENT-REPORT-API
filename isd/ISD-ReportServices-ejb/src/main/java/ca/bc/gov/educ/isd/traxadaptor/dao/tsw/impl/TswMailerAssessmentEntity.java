/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
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

import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.COL_STUDENT_NUMBER;
import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.ENTITY_STUDENT_ASSESSMENT;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Entity
@Table(name = ENTITY_STUDENT_ASSESSMENT)
public class TswMailerAssessmentEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Size(max = 10)
    @Column(name = COL_STUDENT_NUMBER, insertable = false, updatable = false)
    //STUD_NO
    private String studentNumber;

    @Size(max = 5)
    @Column(name = "ASSMT_CODE")
    private String assessmentCode;

    @Size(max = 6)
    @Column(name = "ASSMT_SESSION")
    private String assessmentSession;

    @Size(max = 1)
    @Column(name = "FORM_NAME")
    private String formName;

    @Column(name = "ASSMT_PROFICIENCY_SCORE")
    private BigDecimal assessmentProficiencyScore;

    @Column(name = "ASSMT_TOTAL_SR")
    private BigDecimal assessmentTotalSelectedResponse;

    @Column(name = "STUD_ASSMT_TOTAL_SR")
    private BigDecimal studentAssessmentTotalSeletedResponse;

    @Column(name = "ASSMT_TOTAL_ER")
    private BigDecimal assessmentTotalExtendedResponse;

    @Column(name = "STUD_ASSMT_TOTAL_ER")
    private BigDecimal studentAssessmentTotalExtendedResponse;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Size(max = 40)
    @Column(name = "ASSMT_SR_NAME_1")
    private String assessmentSelectedResponseName1;
    @Size(max = 40)
    @Column(name = "ASSMT_SR_NAME_2")
    private String assessmentSelectedResponseName2;
    @Size(max = 40)
    @Column(name = "ASSMT_SR_NAME_3")
    private String assessmentSelectedResponseName3;
    @Size(max = 40)
    @Column(name = "ASSMT_SR_NAME_4")
    private String assessmentSelectedResponseName4;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Column(name = "ASSMT_SR_VALUE_1")
    private BigDecimal assessmentSelectedResponseValue1;
    @Column(name = "ASSMT_SR_VALUE_2")
    private BigDecimal assessmentSelectedResponseValue2;
    @Column(name = "ASSMT_SR_VALUE_3")
    private BigDecimal assessmentSelectedResponseValue3;
    @Column(name = "ASSMT_SR_VALUE_4")
    private BigDecimal assessmentSelectedResponseValue4;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Column(name = "TOTAL_ASSMT_SR_VALUE_1")
    private BigDecimal assessmentSelectedResponseTotal1;
    @Column(name = "TOTAL_ASSMT_SR_VALUE_2")
    private BigDecimal assessmentSelectedResponseTotal2;
    @Column(name = "TOTAL_ASSMT_SR_VALUE_3")
    private BigDecimal assessmentSelectedResponseTotal3;
    @Column(name = "TOTAL_ASSMT_SR_VALUE_4")
    private BigDecimal assessmentSelectedResponseTotal4;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Size(max = 40)
    @Column(name = "ASSMT_ER_NAME_1")
    private String assessmentExtendedResponseName1;
    @Size(max = 40)
    @Column(name = "ASSMT_ER_NAME_2")
    private String assessmentExtendedResponseName2;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Column(name = "ASSMT_ER_VALUE_1")
    private BigDecimal assessmentExtendedResponseValue1;
    @Column(name = "ASSMT_ER_VALUE_2")
    private BigDecimal assessmentExtendedResponseValue2;

    // TODO: move to separate class with @Embeddable/@Embedded
    @Column(name = "TOTAL_ASSMT_ER_VALUE_1")
    private BigDecimal assessmentExtendedResponseTotal1;
    @Column(name = "TOTAL_ASSMT_ER_VALUE_2")
    private BigDecimal assessmentExtendedResponseTotal2;

    @Column(name = "UPDATE_DT")
    private BigDecimal updateDate;

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getAssessmentCode() {
        return assessmentCode;
    }

    public String getAssessmentSession() {
        return assessmentSession;
    }

    public String getFormName() {
        return formName;
    }

    public BigDecimal getAssessmentProficiencyScore() {
        return assessmentProficiencyScore;

    }

    public BigDecimal getAssessmentTotalSelectedResponse() {
        return assessmentTotalSelectedResponse;
    }

    public BigDecimal getStudentAssessmentTotalSeletedResponse() {
        return studentAssessmentTotalSeletedResponse;
    }

    public BigDecimal getAssessmentTotalExtendedResponse() {
        return assessmentTotalExtendedResponse;
    }

    public BigDecimal getStudentAssessmentTotalExtendedResponse() {
        return studentAssessmentTotalExtendedResponse;
    }

    public BigDecimal getUpdateDate() {
        return updateDate;
    }

    public BigDecimal getAssessmentSelectedResponseValue1() {
        return assessmentSelectedResponseValue1;
    }

    public BigDecimal getAssessmentSelectedResponseValue2() {
        return assessmentSelectedResponseValue2;
    }

    public BigDecimal getAssessmentSelectedResponseValue3() {
        return assessmentSelectedResponseValue3;
    }

    public BigDecimal getAssessmentSelectedResponseValue4() {
        return assessmentSelectedResponseValue4;
    }

    public BigDecimal getAssessmentSelectedResponseTotal1() {
        return assessmentSelectedResponseTotal1;
    }

    public BigDecimal getAssessmentSelectedResponseTotal2() {
        return assessmentSelectedResponseTotal2;
    }

    public BigDecimal getAssessmentSelectedResponseTotal3() {
        return assessmentSelectedResponseTotal3;
    }

    public BigDecimal getAssessmentSelectedResponseTotal4() {
        return assessmentSelectedResponseTotal4;
    }

    public String getAssessmentSelectedResponseName1() {
        return assessmentSelectedResponseName1;
    }

    public String getAssessmentSelectedResponseName2() {
        return assessmentSelectedResponseName2;
    }

    public String getAssessmentSelectedResponseName3() {
        return assessmentSelectedResponseName3;
    }

    public String getAssessmentSelectedResponseName4() {
        return assessmentSelectedResponseName4;
    }

    public String getAssessmentExtendedResponseName1() {
        return assessmentExtendedResponseName1;
    }

    public String getAssessmentExtendedResponseName2() {
        return assessmentExtendedResponseName2;
    }

    public BigDecimal getAssessmentExtendedResponseValue1() {
        return assessmentExtendedResponseValue1;
    }

    public BigDecimal getAssessmentExtendedResponseValue2() {
        return assessmentExtendedResponseValue2;
    }

    public BigDecimal getAssessmentExtendedResponseTotal1() {
        return assessmentExtendedResponseTotal1;
    }

    public BigDecimal getAssessmentExtendedResponseTotal2() {
        return assessmentExtendedResponseTotal2;
    }
}
