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
import java.util.Date;

/**
 * For searching using CriteriaBuilder
 *
 * @author CGI Information Management Consultants Inc.
 */
public class BetweenDate implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 1L;

    private final String fieldName;
    private final Long startDate;
    private final Long endDate;

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public Date getStartDate() {
        return new Date(startDate);
    }

    public Date getEndDate() {
        return new Date(endDate);
    }

    public BetweenDate(String field, Date start, Date end) {
        this.fieldName = field;
        this.startDate = start.getTime();
        this.endDate = end.getTime();
    }

    @Override
    public Object getValue() {
        return "";
    }

    @Override
    public String toString() {
        return fieldName + " = between " + startDate + " and " + endDate;
    }
}
