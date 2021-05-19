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
public class OrderBy implements SearchObjectFieldValue, Serializable {

    private static final long serialVersionUID = 1L;

    private final String fieldName;
    private Order order;

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    public enum Order {

        ASC, DESC;
    };

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public OrderBy(String field) {
        this(field, Order.ASC);
    }

    public OrderBy(String field, Order order) {
        this.fieldName = field;
        this.order = order;
    }

    @Override
    public String toString() {
        return " Order by " + fieldName;
    }

}
