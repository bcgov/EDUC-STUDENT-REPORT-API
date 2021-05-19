/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateType.java 5479 2016-12-01 23:4#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports;

/**
 * Represents the classifications of certificates. This is used in combination
 * with CertificateReportSubtype to select the certificate to generate.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum CertificateType {

    ADULT("Adult", PaperType.CERTIFICATE_ADULT),
    SCCP("SCCP", PaperType.CERTIFICATE_SCCP),
    REGULAR("Regular", PaperType.CERTIFICATE_REGULAR);

    private final String reportName;
    private final PaperType paperType;

    /**
     * Constructs a new enumerated type for a certificate.
     *
     * @param reportName Certificate subreport template filename.
     */
    private CertificateType(final String reportName, final PaperType paperType) {
        this.reportName = reportName;
        this.paperType = paperType;
    }

    /**
     * Returns the paper type required for printing the certificate.
     *
     * @return A non-null instance for controlling a Xerox printer.
     */
    public PaperType getPaperType() {
        return this.paperType;
    }

    /**
     * Returns the file name for this certificate report type.
     *
     * @return The report file name.
     */
    @Override
    public String toString() {
        return this.reportName;
    }
}
