/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                NonGradReasonImpl.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.grad.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.grad.NonGradReason;

import java.io.Serializable;

/**
 * Represents a predefined reason why graduation requirements were not met. This
 * holds a code and a text reason.
 * <p>
 * @author CGI Information Management Consultants Inc.
 */
public class NonGradReasonImpl extends AbstractDomainEntity
        implements NonGradReason, Serializable {

    private static final long serialVersionUID = 3L;

    private final String code;
    private final String description;

    public NonGradReasonImpl(String reasonCode, String reasonText) {
        this.code = reasonCode;
        this.description = reasonText;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
