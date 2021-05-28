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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.common.party.Identifier;
import ca.bc.gov.educ.isd.common.party.address.PostalAddress;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.school.School;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.List;

/**
 * Represents information about a school that is used on a report.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class SchoolImpl extends AbstractDomainEntity implements School {

    private static final long serialVersionUID = 4L;

    private PostalAddress address = new PostalAddressImpl();
    private String mincode = "";
    private String name = "";
    private String typeIndicator = "";
    private String typeBanner = "";
    private String signatureCode = "";

    public void setAddress(final PostalAddress address) {
        this.address = address;
    }

    public void setMincode(final String mincode) {
        this.mincode = mincode;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTypeIndicator(final String typeIndicator) {
        this.typeIndicator = typeIndicator;
    }

    public void setTypeBanner(final String typeBanner) {
        this.typeBanner = typeBanner;
    }

    public void setSignatureCode(final String code) {
        this.signatureCode = code;
    }

    @Override
    public String getMinistryCode() {
        return this.mincode;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @JsonProperty("address")
    @JsonDeserialize(as = PostalAddressImpl.class)
    public PostalAddress getPostalAddress() {
        return this.address;
    }

    @Override
    public String getTypeIndicator() {
        return this.typeIndicator;
    }

    /**
     * Returns true iff the type indicator is not empty.
     *
     * @return false The type indicator contains an independent school code.
     */
    @Override
    public Boolean isIndependent() {
        return !getTypeIndicator().isEmpty();
    }

    @Override
    public String getTypeBanner() {
        return this.typeBanner;
    }

    @Override
    public String getSignatureCode() {
        return this.signatureCode;
    }

    /**
     * Not used.
     *
     * @return
     */
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Identifier> getIdentifiers() {
        //TODO implement this attribute for future use!
        return null;
    }
}
