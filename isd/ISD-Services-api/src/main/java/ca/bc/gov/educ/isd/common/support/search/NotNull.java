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
 *
 * @author CGI Information Management Consultants Inc.
 */
public class NotNull implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 1L;

    private final String fieldName;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public NotNull(String field) {
        this.fieldName = field;
    }

    @Override
    public String toString() {
        return fieldName + " is not null";
    }

}
