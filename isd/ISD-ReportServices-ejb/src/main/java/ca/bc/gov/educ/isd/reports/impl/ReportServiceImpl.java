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
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.assessment.LiteracyAssessmentReport;
import ca.bc.gov.educ.isd.assessment.NumeracyAssessmentReport;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItem;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import ca.bc.gov.educ.isd.reports.*;
import ca.bc.gov.educ.isd.reports.admin.AdminReport;
import ca.bc.gov.educ.isd.reports.common.impl.AbstractReportService;
import ca.bc.gov.educ.isd.reports.jasper.impl.*;
import ca.bc.gov.educ.isd.transcript.ParameterPredicate;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.common.support.impl.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.common.support.impl.Roles.USER;
import static ca.bc.gov.educ.isd.reports.impl.constants.Roles.*;

/**
 * Provides a mechanism to create reports to fill out and produce a specific
 * format.
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({
    USER,
    USER_REPORTS_EXPORT,
    USER_REPORTS_PEAR,
    USER_REPORTS_SCHOLARSHIPS,
    USER_REPORTS_TRANSCRIPT,
    USER_REPORTS_PACKINGSLIP,
    USER_REPORTS_CERTIFICATES,
    FULFILLMENT_SERVICES_USER})
public class ReportServiceImpl extends AbstractReportService
        implements ReportService {

    private static final String CLASSNAME = ReportServiceImpl.class.getName();
    private static transient final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final long serialVersionUID = 2L;

    /**
     * TODO: The PermitAll is required so that the DocumentDownloadServlet,
     * which exists outside of a locked-down URL, can generate transcripts and
     * other reports for the user. This should be reviewed and its ramifications
     * fully understood.
     *
     * @throws IOException Could not read resources required for filling the
     * report (e.g., resource bundle or report template).
     * @param report The report to fill and export.
     */
    @Override
    @PermitAll
    public ReportDocument export(final Report report) throws IOException {
        return super.export(report);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_TRANSCRIPT, FULFILLMENT_SERVICES_USER})
    public TranscriptReport createTranscriptReport() {
        return new TranscriptReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_PEAR, FULFILLMENT_SERVICES_USER})
    public ProvincialExaminationReport createProvincialExaminationReport() {
        return new ProvincialExaminationReportImpl();
    }

    /**
     * Piggy-back GNA report security on PEAR.
     *
     * @return
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_PEAR, FULFILLMENT_SERVICES_USER})
    public NumeracyAssessmentReport createNumeracyAssessmentReport() {
        return new NumeracyAssessmentReportImpl();
    }
    
    /**
     * Piggy-back GLA report security on PEAR.
     *
     * @return
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_PEAR, FULFILLMENT_SERVICES_USER})
    public LiteracyAssessmentReport createLiteracyAssessmentReport() {
        return new LiteracyAssessmentReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_SCHOLARSHIPS, FULFILLMENT_SERVICES_USER})
    public ScholarshipReport createScholarshipReport() {
        return new ScholarshipReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_PACKINGSLIP, FULFILLMENT_SERVICES_USER})
    public PackingSlipReport createPackingSlipReport() {
        return new PackingSlipReportImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER_REPORTS_CERTIFICATES, FULFILLMENT_SERVICES_USER})
    public CertificateReport createCertificateReport() {
        return new CertificateReportImpl();
    }

    /**
     * TODO: The PermitAll is required so that the DocumentDownloadServlet,
     * which exists outside of a locked-down URL. This should be reviewed and
     * its ramifications fully understood.
     *
     * This method sorts the payment line items.
     *
     * @inheritDoc
     */
    @Override
    @PermitAll
    public ReceiptReport createReceiptReport(final List<Receipt> receipts) {
        for (final Receipt receipt : receipts) {
            final List<PaymentLineItem> lineItems = receipt.getLineItems();
            sort(lineItems);
        }

        return new ReceiptReportImpl(receipts);
    }

    /**
     * Sorts the line items first by description (request type) then by the
     * recipient name.
     *
     * @param lineItems The collection of items to sort, can be null.
     */
    private void sort(final List<PaymentLineItem> lineItems) {
        if (lineItems != null) {
            Collections.sort(lineItems, new Comparator<PaymentLineItem>() {
                @Override
                public int compare(
                        final PaymentLineItem o1,
                        final PaymentLineItem o2) {
                    final CompareToBuilder builder = new CompareToBuilder();
                    builder.append(o1.getDescription(), o2.getDescription());
                    builder.append(o1.getRecipientName(), o2.getRecipientName());

                    return builder.toComparison();
                }
            });
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public ReportDocument createReportDocument(final byte[] bytes) {
        return new ReportDocumentImpl(bytes);
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public ParameterPredicate createParameterPredicate() {
        final String _m = "createParameterPredicate()";
        LOG.entering(CLASSNAME, _m);

        final ParameterPredicate pp = new ParameterPredicateImpl();

        LOG.exiting(CLASSNAME, _m);
        return pp;
    }

    /**
     * @inheritDoc
     */
    @Override
    @RolesAllowed({USER, FULFILLMENT_SERVICES_USER})
    public Parameters createParameters() {
        final String _m = "createParameters()";
        LOG.entering(CLASSNAME, _m);

        final Parameters parameters = new LinkedParameters();

        LOG.exiting(CLASSNAME, _m);
        return parameters;
    }

    /**
     * Based on the type of report, this will return an internal representation
     * of the given report instance that can use JasperReports without exposing
     * said library to the calling client. (Prevents marshaling of JasperReports
     * library classes, which won't be found on the client side.)
     *
     * @param report The report to coerce to a JasperReport implementation.
     * @return The given report coerced into a corresponding JasperReport
     * implementation (e.g., a TranscriptReport becomes a
     * TranscriptJasperReportImpl).
     */
    @Override
    protected JasperReportImpl createJasperReportImpl(final Report report) {
        final JasperReportImpl result;

        if (report instanceof TranscriptReport) {
            result = new TranscriptJasperReportImpl(report);
        } else if (report instanceof AdminReport) {
            result = new AdminJasperReportImpl(report);
        } else {
            result = new JasperReportImpl(report);
        }

        return result;
    }
}
