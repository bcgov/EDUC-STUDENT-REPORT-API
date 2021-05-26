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
package ca.bc.gov.educ.isd.transcript.impl;

import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.grad.GraduationProgramCode;
import ca.bc.gov.educ.isd.transcript.Transcript;
import ca.bc.gov.educ.isd.transcript.TranscriptResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A container for a list of transcript courses associated with a student.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptImpl extends AbstractDomainEntity
        implements Transcript, Serializable {

    private static final long serialVersionUID = 3L;

    private List<TranscriptResult> results = new ArrayList<>();
    private Date issueDate;
    private boolean interim;

    @Override
    public List<TranscriptResult> getResults() {
        return this.results;
    }

    /**
     * Returns the results, unsorted.
     *
     * @param code
     * @return
     * @deprecated Add sorting to ReportServices and remove the sorting from
     * TranscriptServices.
     */
    @Override
    public List<TranscriptResult> getResults(final GraduationProgramCode code) {
        return getResults();
    }

    @Override
    public Date getIssueDate() {
        return this.issueDate;
    }

    @Override
    public boolean isEmpty() {
        return getResults().isEmpty();
    }

    /**
     *
     * @param issueDate
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Set an entire collection of transcript results.
     *
     * @param results
     */
    public void setResults(List<TranscriptResult> results) {
        // Prevents resetting the list to empty.
        if (results != null && !results.isEmpty()) {
            this.results = results;
        }
    }

    /**
     * Add an individual course to the transcript results list.
     *
     * @param result A transcript result to add to the list.
     */
    public void addResult(TranscriptResult result) {
        if (result != null) {
            getResults().add(result);
        }
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void setInterim(boolean interim) {
        this.interim = interim;
    }

    public boolean getInterim() {
        return this.interim;
    }
}
