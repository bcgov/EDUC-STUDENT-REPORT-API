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
 *  Last Commit by:      $Author:-:                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common.support.search;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.nullSafe;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/**
 * This is used for case insensitive searches in the database. This is
 * accomplished through setting both parameters in the query to upper case.
 *
 * @author jack.staples
 */
public class UpperCaseCriteriaBuilder {

    private final CriteriaBuilder criteriaBuilder;

    public UpperCaseCriteriaBuilder(final CriteriaBuilder cb) {
        this.criteriaBuilder = cb;
    }

    public Predicate like(final Expression<String> expression, final String pattern) {

        final CriteriaBuilder cb = getCriteriaBuilder();

        final Expression<String> upperCaseExpression = cb.upper(expression);
        final String upperCasePattern = nullSafe(pattern).toUpperCase();

        final Predicate retVal = cb.like(upperCaseExpression, upperCasePattern);

        return retVal;
    }

    private CriteriaBuilder getCriteriaBuilder() {
        return this.criteriaBuilder;
    }

}
