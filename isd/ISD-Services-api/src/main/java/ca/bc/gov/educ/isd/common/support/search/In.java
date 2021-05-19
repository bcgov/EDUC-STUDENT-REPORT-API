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
import ca.bc.gov.educ.isd.common.support.PrintUtils;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * For searching using CriteriaBuilder.
 *
 * FIXME: Change List of Object to Set of Object.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class In implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 2L;

    private final String fieldName;
    private final List<Object> values;

    /**
     * Creates a new SQL "IN" clause for comparing the given field against the
     * given list of values.
     *
     * @param field The database column name to compare against.
     * @param values The set of values to match against the field.
     */
    public In(final String field, final List<Object> values) {
        this.fieldName = field;
        this.values = values;
    }

    /**
     * Creates a new "IN" clause by casting the given array of strings to a list
     * of objects.
     *
     * @param field The database column name to compare against.
     * @param values The set of values to match against the field.
     */
    public In(final String field, final String[] values) {
        this(field, Arrays.asList((Object[]) values));
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    public List<Object> getValues() {
        return this.values;
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String toString() {
        return getFieldName() + " in (" + PrintUtils.commaDelimited(getValues()) + ")";
    }

}
