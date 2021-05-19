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
 * This is used for searching within a map that is part of the entity being
 * searched.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class MemberMapSearch implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 1L;

    private final String fieldName;
    private final Object value;
    private final String key;

    /**
     * Constructor for the field of interest and the search to be used for the
     * value.
     *
     * For example, the field could be "taskAttributes" (a map). The key name is
     * the map key (for instance "Entity Id") and the value is what this field
     * should be. Currently only LIKE functionality and joins not possible.
     *
     * @param fieldName
     * @param key
     * @param value
     */
    public MemberMapSearch(String fieldName, String key, Object value) {
        this.fieldName = fieldName;
        this.key = key;
        this.value = value;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return fieldName + ".get(" + key + ") = " + value;
    }

}
