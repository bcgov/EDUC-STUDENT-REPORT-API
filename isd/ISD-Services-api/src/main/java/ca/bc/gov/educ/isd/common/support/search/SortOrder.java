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

import ca.bc.gov.educ.isd.common.SortObject;
import java.io.Serializable;

/**
 * Implementation of SortObject.
 */
public class SortOrder implements SortObject, Serializable {

    private static final long serialVersionUID = 2L;

    private final String fieldName;
    private final Boolean ascending;

    public SortOrder(final String fieldName, final Boolean ascending) {
        this.fieldName = fieldName;
        this.ascending = ascending;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Boolean isAscending() {
        return ascending;
    }
}
