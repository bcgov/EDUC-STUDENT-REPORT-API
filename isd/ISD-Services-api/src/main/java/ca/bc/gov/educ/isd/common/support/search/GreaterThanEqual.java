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

import ca.bc.gov.educ.isd.common.SearchObjectFieldValue;
import java.io.Serializable;

/**
 * For searching using CriteriaBuilder.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class GreaterThanEqual implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 1L;

    private final String fieldName;
    private final Double value;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Double getValue() {
        return value;
    }

    public GreaterThanEqual(String field, Double value) {
        this.fieldName = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return fieldName + " >= " + value;
    }

}
