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
package ca.bc.gov.educ.isd.common.support.search;

/**
 * For searching using CriteriaBuilder.
 *
 * UpperCaseLike is identical to the Like class. It is a necessary flag that is
 * used to indicate that a query which sets both what is found in the database
 * and the search pattern to upper case before comparing. This mimics a case
 * insensitive query.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class UpperCaseLike extends Like {

    public UpperCaseLike(String field, String value) {
        super(field, value);
    }
}
