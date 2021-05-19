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
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;

import javax.persistence.Transient;
import java.util.Objects;

/**
 * <p>
 * JPA implementation class for the <code>PersonalEducationNumber</code>
 * interface.</p>
 *
 * @author CGI Information Management Consultants Inc.
 */

public class PersonalEducationNumberImpl extends AbstractDomainEntity implements PersonalEducationNumber {

    private static final long serialVersionUID = 1L;

    private String nameCode;

    private String typeCode;
    private String value;
    private Long id;
    private String issuedByOrganizationEntityId;
    private boolean active;
    private StudentXRefEntity studentXRefEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // TODO issue with column name being too long for db, 30+ characters
    /**
     * Gets the User Profile associated with the identity.
     *
     * @return UserProfile used with this type of identity.
     */

    public StudentXRefEntity getStudentXRefEntity() {
        return studentXRefEntity;
    }

    /**
     * Sets the Student X Ref.
     *
     * @param studentXRefEntity
     */
    public void setStudentXRefEntity(final StudentXRefEntity studentXRefEntity) {
        this.studentXRefEntity = studentXRefEntity;
    }

    @Override
    @Transient
    public String getNameCode() {
        return nameCode;
    }

    @Override
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    @Override
    @Transient
    public String getIssuedByOrganizationEntityId() {
        return issuedByOrganizationEntityId;
    }

    @Override
    public void setIssuedByOrganizationEntityId(String issuedByOrganizationEntityId) {
        this.issuedByOrganizationEntityId = issuedByOrganizationEntityId;
    }

    /**
     * Returns a student's personal education number. This returns a value that
     * is suitable to use when searching for a PEN in the student master table.
     *
     * @return A non-null String.
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the student's personal education number.
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    @Transient
    public String getTypeCode() {
        return typeCode;
    }

    @Override
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    @Transient
    public boolean isActive() {
        return active;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonalEducationNumberImpl other = (PersonalEducationNumberImpl) obj;
        return Objects.equals(this.value, other.value);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "nameCode=" + nameCode + ", typeCode=" + typeCode + ", value=" + value + ", id=" + id + ", issuedByOrganizationEntityId=" + issuedByOrganizationEntityId + ", active=" + active + ", studentXRefEntity=" + studentXRefEntity + '}';
    }
}
