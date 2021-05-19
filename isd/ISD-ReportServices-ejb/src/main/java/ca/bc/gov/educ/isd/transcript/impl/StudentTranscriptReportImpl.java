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

import ca.bc.gov.educ.isd.reports.ReportFormat;
import ca.bc.gov.educ.isd.transcript.StudentTranscriptReport;
import ca.bc.gov.educ.isd.common.support.report.BusinessReportEntity;

/**
 * The underlying implementation for the GUI. This is an intermediary between
 * the GUI and the report service to loosely couple the producer and the
 * consumer. It provides the data necessary for the GUI to display an unofficial
 * transcript of grades report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class StudentTranscriptReportImpl extends BusinessReportEntity
        implements StudentTranscriptReport {

    private static final long serialVersionUID = 3L;

    /**
     * Class Constructor populates all the object attributes during
     * construction.
     *
     * @param reportData
     * @param reportFormat
     * @param reportName
     * @param filename
     */
    public StudentTranscriptReportImpl(
            final byte[] reportData,
            final ReportFormat reportFormat,
            final String filename,
            final String reportName) {
        super(reportData, reportFormat, filename, reportName);
    }
}
