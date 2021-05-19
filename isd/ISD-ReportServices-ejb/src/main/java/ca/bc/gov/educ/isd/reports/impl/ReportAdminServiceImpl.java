/* *********************************************************************
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
 * ********************************************************************** */
package ca.bc.gov.educ.isd.reports.impl;

import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Payment;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentAdmin;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItem;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentLineItemAdmin;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentMethod;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.PaymentStatus;
import ca.bc.gov.educ.isd.ecommerce.payment.receipt.Receipt;
import ca.bc.gov.educ.isd.reports.ReportAdminService;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.PaymentAdminImpl;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.PaymentLineItemAdminImpl;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.PaymentMethodImpl;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.PaymentStatusImpl;
import ca.bc.gov.educ.isd.reports.data.receipt.impl.ReceiptImpl;
import java.util.List;
import javax.annotation.security.PermitAll;

/**
 * Provides a mechanism to create reports to fill out and produce a specific
 * format.
 *
 * @author CGI Information Management Consultants Inc.
 */
@PermitAll
public class ReportAdminServiceImpl implements ReportAdminService {

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentAdmin createPayment() {
        return new PaymentAdminImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentLineItemAdmin createPaymentLineItemAdmin() {
        return new PaymentLineItemAdminImpl();
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentMethod createPaymentMethod(final String code, final String description) {
        return new PaymentMethodImpl(code, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public PaymentStatus createPaymentStatus(final String code, final String description) {
        return new PaymentStatusImpl(code, description);
    }

    /**
     * @inheritDoc
     */
    @Override
    @PermitAll
    public Receipt createReceipt(
            final Payment payment,
            final List<PaymentLineItem> lineItems) {
        return new ReceiptImpl(payment, lineItems);
    }
}
