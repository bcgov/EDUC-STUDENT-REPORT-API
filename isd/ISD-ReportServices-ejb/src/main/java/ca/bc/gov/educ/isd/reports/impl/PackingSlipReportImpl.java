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
 *  File:                $Id:: PackingSlipReportImpl.java 5882 2017-01-19 20:2#$
 *  Date of Last Commit: $Date:: 2017-01-19 12:28:21 -0800 (Thu, 19 Jan 2017)  $
 *  Revision Number:     $Rev:: 5882                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.reports.packingslip.DestinationType;
import ca.bc.gov.educ.isd.reports.bundle.service.OrderType;
import ca.bc.gov.educ.isd.reports.PackingSlipReport;
import ca.bc.gov.educ.isd.reports.data.adapter.BusinessEntityAdapter;
import ca.bc.gov.educ.isd.reports.data.impl.PackingSlipDetails;
import ca.bc.gov.educ.isd.reports.jasper.impl.ReportImpl;

/**
 * Represents information required to generate a packing slip.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class PackingSlipReportImpl extends ReportImpl
        implements PackingSlipReport {

    private static final long serialVersionUID = 3L;

    public final static String REPORT_NAME = "PackingSlip";

    /**
     * Information to include on the packing slip.
     */
    private PackingSlipDetails packingSlipDetails;

    /**
     * Constructs a new report using the default report template for packing
     * slips.
     */
    public PackingSlipReportImpl() {
        super(REPORT_NAME);
    }

    /**
     * Returns the main object used to fill the report.
     *
     * @return this.packingSlipDetails
     */
    @Override
    public Object getDataSource() {
        return this.packingSlipDetails;
    }

    /**
     * Sets the information to write on the packing slip.
     *
     * @param details Mailing address and contents overview details.
     */
    @Override
    public void setPackingSlipDetails(
            final ca.bc.gov.educ.isd.reports.packingslip.PackingSlipDetails details) {
        this.packingSlipDetails = BusinessEntityAdapter.adapt(details);
    }

    /**
     * Sets the order type so that the report can determine what fields to
     * include, and whether to add a blank page. Delegates to the packing slip
     * details instance.
     *
     * @param orderType Either TRANSCRIPT or CERTIFICATE.
     * @throws NullPointerException if the order type is null.
     */
    @Override
    public void setOrderType(final OrderType orderType) {
        if (orderType == null) {
            throw new NullPointerException("Order type is null (must be transcript or certificate).");
        }

        getPackingSlipDetails().setOrderTypeName(orderType.getName());
    }

    /**
     * Sets the name of the destination for where the print materials are slated
     * to be delivered. This will be either a student or PSI. Delegates to the
     * packing slip details instance.
     *
     * @param destinationType PSI or null.
     */
    @Override
    public void setDestinationType(final DestinationType destinationType) {
        if (destinationType != null) {
            getPackingSlipDetails().setDestinationTypeName(
                    destinationType.toString());
        }
    }

    /**
     * Returns a filename suitable for saving this report. Builds up a string
     * based on the packing slip details settings.
     *
     * @return A non-null file instance.
     */
    @Override
    public String getFilename() {
        final StringBuilder result = new StringBuilder(128);
        result.append(getName());

        String s = getPackingSlipDetails().getOrderTypeName();
        result.append('-').append(s);

        s = getPackingSlipDetails().getDestinationTypeName();
        result.append('-').append(s);
        result.append(getFilenameExtension());

        return result.toString();
    }

    /**
     * Returns the packing slip details to use for populating the report.
     *
     * @return A non-null instance.
     */
    private PackingSlipDetails getPackingSlipDetails() {
        return this.packingSlipDetails;
    }
}
