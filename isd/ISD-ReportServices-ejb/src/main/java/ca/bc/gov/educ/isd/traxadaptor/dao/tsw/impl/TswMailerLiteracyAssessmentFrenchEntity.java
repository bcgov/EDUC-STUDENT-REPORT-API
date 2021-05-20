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
@Table(name = "TSW_MAILER_ASSMT_DTL_LTP10_VW")
public class TswMailerLiteracyAssessmentFrenchEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Size(max = 10)
    @Column(name = COL_STUDENT_NUMBER, insertable = false, updatable = false)
    //STUD_NO
    private String studentNumber;

    @Size(max = 10)
    @Column(name = "ASSMT_CODE")
    private String assessmentCode;

    @Size(max = 10)
    @Column(name = "ASSMT_SESSION")
    private String assessmentSession;

    @Size(max = 1)
    @Column(name = "FORM_CODE")
    private String formName;

    @Column(name = "ASSMT_PROFICIENCY_SCORE")
    private BigDecimal assessmentProficiencyScore;

    @Column(name = "ASSMT_TOTAL_CLAIM_C")
    private BigDecimal assessmentTotalClaimC;

    @Column(name = "STUD_ASSMT_TOTAL_CLAIM_C")
    private BigDecimal studentAssessmentTotalClaimC;

    @Column(name = "ASSMT_NAME_CLAIM_C")
    private String assessmentNameClaimC;
    
    @Column(name = "ASSMT_TOTAL_CLAIM_W")
    private BigDecimal assessmentTotalClaimW;

    @Column(name = "STUD_ASSMT_TOTAL_CLAIM_W")
    private BigDecimal studentAssessmentTotalClaimW;

    @Column(name = "ASSMT_NAME_CLAIM_W")
    private String assessmentNameClaimW;
    
    @Column(name = "ASSMT_TOTAL_CLAIM_O")
    private BigDecimal assessmentTotalClaimO;

    @Column(name = "STUD_ASSMT_TOTAL_CLAIM_O")
    private BigDecimal studentAssessmentTotalClaimO;

    @Column(name = "ASSMT_NAME_CLAIM_O")
    private String assessmentNameClaimO;

    @Column(name = "ASSMT_SR_TOTAL_TASK_A")
    private BigDecimal assessmentSelectedResponseTotalTaskA;

    @Column(name = "ASSMT_SR_VALUE_TASK_A")
    private BigDecimal assessmentSelectedResponseValueTaskA;

    @Column(name = "ASSMT_SR_NAME_TASK_A")
    private String assessmentSelectedResponseNameTaskA;

    @Column(name = "ASSMT_SR_TOTAL_TASK_E_I")
    private BigDecimal assessmentSelectedResponseTotalTaskEI;

    @Column(name = "ASSMT_SR_VALUE_TASK_E_I")
    private BigDecimal assessmentSelectedResponseValueTaskEI;

    @Column(name = "ASSMT_SR_NAME_TASK_E_I")
    private String assessmentSelectedResponseNameTaskEI;
    
    @Column(name = "ASSMT_ER_TOTAL_TASK_E_I")
    private BigDecimal assessmentExtendedResponseTotalTaskEI;

    @Column(name = "ASSMT_ER_VALUE_TASK_E_I")
    private BigDecimal assessmentExtendedResponseValueTaskEI;

    @Column(name = "ASSMT_ER_NAME_TASK_E_I")
    private String assessmentExtendedResponseNameTaskEI;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_GO")
    private BigDecimal assessmentExtendedResponseTotalConceptsGO;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_GO")
    private BigDecimal assessmentExtendedResponseValueConceptsGO;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_GO")
    private String assessmentExtendedResponseNameConceptsGO;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_WRA")
    private BigDecimal assessmentExtendedResponseTotalConceptsWRA;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_WRA")
    private BigDecimal assessmentExtendedResponseValueConceptsWRA;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_WRA")
    private String assessmentExtendedResponseNameConceptsWRA;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_WRS")
    private BigDecimal assessmentExtendedResponseTotalConceptsWRS;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_WRS")
    private BigDecimal assessmentExtendedResponseValueConceptsWRS;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_WRS")
    private String assessmentExtendedResponseNameConceptsWRS;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_O1")
    private BigDecimal assessmentTotalConcepts1;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_O1")
    private BigDecimal assessmentValueConcepts1;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_O1")
    private String assessmentNameConcepts1;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_O2")
    private BigDecimal assessmentTotalConcepts2;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_O2")
    private BigDecimal assessmentValueConcepts2;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_O2")
    private String assessmentNameConcepts2;
    
    @Column(name = "ASSMT_ER_TOTAL_CONCEPTS_O3")
    private BigDecimal assessmentTotalConcepts3;

    @Column(name = "ASSMT_ER_VALUE_CONCEPTS_O3")
    private BigDecimal assessmentValueConcepts3;

    @Column(name = "ASSMT_ER_NAME_CONCEPTS_O3")
    private String assessmentNameConcepts3;

    @Column(name = "UPDATE_DT")
    private BigDecimal updateDate;
    
    @Column(name = "CHOICE_PATH_ID")
    private String choicePathID;

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

    public BigDecimal getAssessmentTotalClaimC() {
        return assessmentTotalClaimC;
    }

    public BigDecimal getStudentAssessmentTotalClaimC() {
        return studentAssessmentTotalClaimC;
    }

    public String getAssessmentNameClaimC() {
        return assessmentNameClaimC;
    }
    
    public BigDecimal getAssessmentTotalClaimW() {
        return assessmentTotalClaimW;
    }

    public BigDecimal getStudentAssessmentTotalClaimW() {
        return studentAssessmentTotalClaimW;
    }

    public String getAssessmentNameClaimW() {
        return assessmentNameClaimW;
    }
    
    public BigDecimal getAssessmentTotalClaimO() {
        return assessmentTotalClaimO;
    }

    public BigDecimal getStudentAssessmentTotalClaimO() {
        return studentAssessmentTotalClaimO;
    }

    public String getAssessmentNameClaimO() {
        return assessmentNameClaimO;
    }
    
    public BigDecimal getAssessmentSelectedResponseTotalTaskA() {
        return assessmentSelectedResponseTotalTaskA;
    }

    public BigDecimal getAssessmentSelectedResponseValueTaskA() {
        return assessmentSelectedResponseValueTaskA;
    }

    public String getAssessmentSelectedResponseNameTaskA() {
        return assessmentSelectedResponseNameTaskA;
    }
    
    public BigDecimal getAssessmentSelectedResponseTotalTaskEI() {
        return assessmentSelectedResponseTotalTaskEI;
    }

    public BigDecimal getAssessmentSelectedResponseValueTaskEI() {
        return assessmentSelectedResponseValueTaskEI;
    }

    public String getAssessmentSelectedResponseNameTaskEI() {
        return assessmentSelectedResponseNameTaskEI;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalTaskEI() {
        return assessmentExtendedResponseTotalTaskEI;
    }

    public BigDecimal getAssessmentExtendedResponseValueTaskEI() {
        return assessmentExtendedResponseValueTaskEI;
    }

    public String getAssessmentExtendedResponseNameTaskEI() {
        return assessmentExtendedResponseNameTaskEI;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsGO() {
        return assessmentExtendedResponseTotalConceptsGO;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsGO() {
        return assessmentExtendedResponseValueConceptsGO;
    }

    public String getAssessmentExtendedResponseNameConceptsGO() {
        return assessmentExtendedResponseNameConceptsGO;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsWRA() {
        return assessmentExtendedResponseTotalConceptsWRA;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsWRA() {
        return assessmentExtendedResponseValueConceptsWRA;
    }

    public String getAssessmentExtendedResponseNameConceptsWRA() {
        return assessmentExtendedResponseNameConceptsWRA;
    }
    
    public BigDecimal getAssessmentExtendedResponseTotalConceptsWRS() {
        return assessmentExtendedResponseTotalConceptsWRS;
    }

    public BigDecimal getAssessmentExtendedResponseValueConceptsWRS() {
        return assessmentExtendedResponseValueConceptsWRS;
    }

    public String getAssessmentExtendedResponseNameConceptsWRS() {
        return assessmentExtendedResponseNameConceptsWRS;
    }
    
    public BigDecimal getAssessmentTotalConcepts1() {
        return assessmentTotalConcepts1;
    }
    
    public BigDecimal getAssessmentValueConcepts1() {
        return assessmentValueConcepts1;
    }
    
    public String getAssessmentNameConcepts1() {
        return assessmentNameConcepts1;
    }
    
    public BigDecimal getAssessmentTotalConcepts2() {
        return assessmentTotalConcepts2;
    }
    
    public BigDecimal getAssessmentValueConcepts2() {
        return assessmentValueConcepts2;
    }
    
    public String getAssessmentNameConcepts2() {
        return assessmentNameConcepts2;
    }
    
    public BigDecimal getAssessmentTotalConcepts3() {
        return assessmentTotalConcepts3;
    }
    
    public BigDecimal getAssessmentValueConcepts3() {
        return assessmentValueConcepts3;
    }
    
    public String getAssessmentNameConcepts3() {
        return assessmentNameConcepts3;
    }
    
    public BigDecimal getUpdateDate() {
        return updateDate;
    }
    
    public String getChoicePathID() {
        return choicePathID;
    }
}
