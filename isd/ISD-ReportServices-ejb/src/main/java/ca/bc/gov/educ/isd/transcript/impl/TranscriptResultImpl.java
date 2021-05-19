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
import ca.bc.gov.educ.isd.transcript.Course;
import ca.bc.gov.educ.isd.transcript.Mark;
import ca.bc.gov.educ.isd.transcript.TranscriptResult;

/**
 * A transcript result contains a course and the marks.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class TranscriptResultImpl extends AbstractDomainEntity
        implements TranscriptResult {

    private static final long serialVersionUID = 4L;

    private Course course;
    private Mark mark;
    private String requirement = "";
    private String requirementName = "";
    private String equivalency = "";
    private String usedForGrad = "";

    public TranscriptResultImpl() {
    }

    public TranscriptResultImpl(final Course course, final Mark mark) {
        this.course = course;
        this.mark = mark;
    }

    public TranscriptResultImpl(final String req, final String equiv, final String ufg, final String reqName) {
        this.equivalency = equiv;
        this.requirement = req;
        this.usedForGrad = ufg;
        this.requirementName = reqName;
    }

    @Override
    public Course getCourse() {
        return this.course;
    }

    @Override
    public Mark getMark() {
        return this.mark;
    }

    @Override
    public String getRequirementMet() {
        return this.requirement;
    }

    @Override
    public String getRequirementMetName() {
        return this.requirementName;
    }

    @Override
    public String getEquivalencyChallenge() {
        return equivalency;
    }

    @Override
    public String getUsedForGrad() {
        return usedForGrad;
    }

    public void setCourse(Course course) {
        if (course != null) {
            this.course = course;
        }
    }

    public void setMark(Mark mark) {
        if (mark != null) {
            this.mark = mark;
        }
    }

    /**
     * Throws UnsupportedOperationException.
     *
     * @return Not used.
     */
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
