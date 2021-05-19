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

import ca.bc.gov.educ.isd.common.SearchObject;
import java.io.Serializable;

/**
 * For searching using CriteriaBuilder.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AndJoin implements SearchObject, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "and";
    }

}
