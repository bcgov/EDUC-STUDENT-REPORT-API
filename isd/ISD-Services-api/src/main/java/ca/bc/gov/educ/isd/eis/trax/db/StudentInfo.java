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
package ca.bc.gov.educ.isd.eis.trax.db;

import ca.bc.gov.educ.isd.eis.grad.GraduationProgramCode;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Defines the public methods to access the Student information used by the
 * transcript services
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentInfo extends TRAXData, TRAXCountry {

    /**
     * get the PEN which the data corresponds to.
     *
     * @return PEN
     */
    String getPen();

    /**
     * get the student's first name.
     *
     * @return first name
     */
    String getFirstName();

    /**
     * get the student's middle name.
     *
     * @return middle name
     */
    String getMiddleName();

    /**
     * get the student's last name.
     *
     * @return last name
     */
    String getLastName();

    /**
     * get the student's date of birth. Format YYYYMMDD.
     *
     * @return date of birth
     */
    Date getBirthDate();

    /**
     * get the student's address field #1.
     *
     * @return first student address field
     */
    String getStudentAddress1();

    /**
     * get the student's address field #2.
     *
     * @return second student address field
     */
    String getStudentAddress2();

    /**
     * get the student's address city.
     *
     * @return address city
     */
    String getStudentCity();

    /**
     * get the student's address province name.
     *
     * @return address province
     */
    String getStudentProv();

    /**
     * get the student's living status.
     *
     * @return status
     */
    Character getStudentStatus();

    /**
     * get the student gender.
     *
     * @return M for male or F for female
     */
    Character getGender();

    /**
     * get the student honours title.
     *
     * @return True if student is an honours student
     */
    Boolean isHonourFlag();

    /**
     * get the students completion indicator.
     *
     * @return True if student completed the requirements for a diploma
     */
    Boolean isDogwoodFlag();

    /**
     * get the student's grade.
     *
     * @return grade
     */
    String getGrade();

    /**
     * get the student's grade date. Format YYYYMM
     *
     * @return graduation date
     */
    Date getGradDate();

    /**
     * get the graduation program the student is associated with. This outlines
     * what requirements are used to determine graduation requirements.
     *
     * @return graduation requirements year
     */
    String getGradProgram();

    /**
     * Returns the graduation program code enumeration for this student's
     * graduation program.
     *
     * @return A non-null instance.
     * @throws IllegalArgumentException The gradProgram is not represented by
     * GraduationProgramCode.
     */
    GraduationProgramCode getGraduationProgramCode();

    /**
     * get the code reference for the student's secondary school.
     *
     * @return ministry school code
     */
    String getMincode();

    /**
     * get the secondary school name.
     *
     * @return school name
     */
    String getSchoolName();

    /**
     * get the secondary school street address.
     *
     * @return school street address
     */
    String getSchoolStreet();

    /**
     * get the secondary school street address field #2.
     *
     * @return
     */
    String getSchoolStreet2();

    /**
     * get the secondary school city.
     *
     * @return school city
     */
    String getSchoolCity();

    /**
     * get the secondary school postal code.
     *
     * @return postal code
     */
    String getSchoolPostalCode();

    /**
     * get the secondary school province name.
     *
     * @return province
     */
    String getSchoolProv();

    /**
     * get the secondary school phone number.
     *
     * @return school phone number, unformatted
     */
    String getSchoolPhone();

    /**
     * get the reasons why the student has not met the graduation requirements.
     *
     * @return reasons for not graduating
     */
    Map<String, String> getNonGradReasons();

    /**
     * get the date when the UTG record was last updated.
     *
     * @return date UTG last updated
     */
    Date getLastUpdateDate();

    /**
     * get the display logo.
     *
     * @return logo; values are BC or YU
     */
    String getLogo();

    /**
     * get the date when the UTG record was last issued.
     *
     * @return date UTG last issued
     */
    Date getReportDate();

    /**
     * get the student address postal code.
     *
     * @return postal code
     */
    String getStudentPostalCode();

    /**
     * get the school assigned student ID.
     *
     * @return students school ID
     */
    String getSchoolId();

    /**
     * get the list of academic program name fields.
     *
     * @return List of academic program name fields
     */
    List<String> getAcademicProgram();

    /**
     * Used to indicate whether this is an independent school.
     *
     * @return Blank or digits 1 - 6, never null, no space.
     */
    String getSchoolTypeIndicator();

    /**
     * get school type banner. Value depends on school type.
     *
     * @return
     */
    String getSchoolTypeBanner();

    /**
     * get the graduation message.
     * <p>
     * @return
     */
    String getGradMessage();

    /**
     * get the student address TRAX country code.
     *
     * @return
     */
    @Override
    String getTRAXCountryCode();

    /**
     * get the student address ISO country code.
     *
     *
     * @return
     */
    @Override
    String getCountryCode();

}
