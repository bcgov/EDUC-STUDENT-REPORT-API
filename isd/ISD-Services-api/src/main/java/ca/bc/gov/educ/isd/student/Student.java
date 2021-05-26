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
 *  File:                $Id:: Student.java 8289 2017-09-26 23:04:07Z CGOMEZTE $
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.party.Person;
import ca.bc.gov.educ.isd.common.party.address.PostalAddress;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * Represents a student business entity.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Student extends Person {

    /**
     * Retrieve the Student PEN.
     *
     * The PEN is an identifier given to students. It is possible that a student
     * does not have a PEN assign or that a duplicate PEN exists.
     *
     * @return
     */
    PersonalEducationNumber getPen();

    /**
     * Date the student was born.
     *
     * @return A non-null date.
     */
    Date getBirthdate();

    /**
     * Student's current grade.
     *
     * @return Current grade.
     */
    String getGrade();

    /**
     * Returns information about where to send physical items (such as the
     * student's transcripts). In the future, this might change to accommodate
     * international mailing addresses.
     *
     * @return A non-null mailing address.
     */
    PostalAddress getCurrentMailingAddress();
}
