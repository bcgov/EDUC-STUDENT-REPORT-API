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
package ca.bc.gov.educ.isd.grad.impl;

import ca.bc.gov.educ.isd.cert.Certificate;
import ca.bc.gov.educ.isd.common.BusinessProcessException;
import ca.bc.gov.educ.isd.common.Constants;
import static ca.bc.gov.educ.isd.common.Constants.DATE_ISO_8601_FULL;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.party.Identifier;
import ca.bc.gov.educ.isd.common.party.address.Address;
import static ca.bc.gov.educ.isd.common.support.impl.Roles.USER;
import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.StudentDemographic;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXAdapter;
import ca.bc.gov.educ.isd.grad.GradCertificateReport;
import ca.bc.gov.educ.isd.grad.GradCertificateService;
import static ca.bc.gov.educ.isd.grad.GraduationProgramCode.PROGRAM_SCCP;
import ca.bc.gov.educ.isd.reports.CertificateReport;
import ca.bc.gov.educ.isd.reports.CertificateSubtype;
import ca.bc.gov.educ.isd.reports.CertificateType;
import static ca.bc.gov.educ.isd.reports.CertificateType.REGULAR;
import ca.bc.gov.educ.isd.reports.ReportDocument;
import static ca.bc.gov.educ.isd.reports.ReportFormat.PDF;
import ca.bc.gov.educ.isd.reports.ReportService;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.student.StudentXRef;
import ca.bc.gov.educ.isd.student.StudentXRefService;
import ca.bc.gov.educ.isd.student.impl.SchoolImpl;
import ca.bc.gov.educ.isd.student.impl.StudentImpl;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static ca.bc.gov.educ.isd.transcript.impl.constants.Roles.STUDENT_CERTIFICATE_REPORT;
import static java.util.Locale.CANADA;
import static java.util.Locale.CANADA_FRENCH;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Service
@DeclareRoles({STUDENT_CERTIFICATE_REPORT, USER})
public class GradCertificateServiceImpl
        implements GradCertificateService, Serializable {

    private static final long serialVersionUID = 2L;
    private static final String CLASSNAME = GradCertificateServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private TRAXAdapter traxAdapter;

    private ReportService reportService;

    private StudentXRefService studentxref;

    @RolesAllowed({STUDENT_CERTIFICATE_REPORT, USER})
    @Override
    public List<GradCertificateReport> buildReport() throws DomainServiceException {
        final String _m = "buildReport()";
        LOG.entering(CLASSNAME, _m);

        // access student demographics to obtain PEN
        PersonalEducationNumber penObj = null;
        String penId = null;

        if (studentxref.exists()) {
            final StudentXRef sxref = studentxref.read();
            penObj = sxref != null ? sxref.getPen() : null;
            penId = penObj != null ? penObj.getValue() : null;
        }

        if (penObj == null) {
            final String msg = "The current user is not a student with a PEN.";
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        LOG.log(Level.FINE,
                "Confirmed the user is a student and retrieved the PEN.");

        // access TRAX adaptor to obtain required data for PEN
        final StudentDemographic studentData = readStudentDemog(penId);
        if (studentData == null) {
            final String msg = "Failed to find student demographic information in TRAX for PEN: " + penId;
            final DomainServiceException dse = new DomainServiceException(msg);
            LOG.throwing(CLASSNAME, _m, dse);
            throw dse;
        }

        // transfer TRAX data to other objects for reporting
        final Student student = transferStudentData(penObj, studentData);
        final School school = transferSchoolData(studentData);
        final String penEntityId = penObj.getEntityId();
        final Certificate certificate = new CertificateImpl(
                studentData.getCertificateDate());

        final List<GradCertificateReport> certificates = new ArrayList<>();
        final String englishCert = studentData.getEnglishCertificate().trim();
        final String frenchCert = studentData.getFrenchCertificate().trim();
        final boolean sccp = PROGRAM_SCCP.isCode(studentData.getCertificateCategory());

        LOG.log(Level.FINE, "EnglishCert flag: {0}", englishCert);
        LOG.log(Level.FINE, "SCCP flag: {0}", sccp);
        LOG.log(Level.FINE, "FrenchCert flag: {0}", frenchCert);

        if (!englishCert.isEmpty() || sccp) {
            final String certType = studentData.getEnCertType();

            final GradCertificateReport gradCert = englishCertificate(
                    certType, student, school, penEntityId, certificate);

            certificates.add(gradCert);
        }

        if (!frenchCert.isEmpty()) {
            final String certType = studentData.getFrCertType();

            final GradCertificateReport gradCert = frenchCertificate(
                    certType, student, school, penEntityId, certificate);

            certificates.add(gradCert);
        }

        LOG.exiting(CLASSNAME, _m);
        return certificates;
    }

    @Override
    public Boolean checkGrad(PersonalEducationNumber pen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String sendReport(String orderXRef, Identifier partyId, Address addr) throws BusinessProcessException, DomainServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String checkDelivery(String refNo) throws DomainServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> listTrackingNo(String orderXRef) throws DomainServiceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Call TRAX adaptor to perform a read of the student demographics.
     *
     * @param pen
     * @return TRAX StudentDemographic
     * @throws DataException
     */
    private StudentDemographic readStudentDemog(final String pen) throws DataException {
        final String methodName = "readStatic(String)";
        LOG.entering(CLASSNAME, methodName, pen);

        final StudentDemographic studentInfo;
        try {
            studentInfo = traxAdapter.readStudent_Demographic(pen);

            LOG.log(Level.FINER, "Retrieved student demographic data from TRAX for PEN: {0}", pen);

            if (studentInfo != null) {
                LOG.log(Level.FINEST, "Retrieved student:");
                LOG.log(Level.FINEST, "{0} {1} {2}",
                        new Object[]{studentInfo.getPen(), studentInfo.getFirstName(), studentInfo.getLastName()});
            }
        } catch (final EISException ex) {
            final String msg = "Failed to access TRAX student demographic data for student with PEN: ".concat(
                    pen);
            final DataException dex = new DataException(null, null, msg, ex);
            LOG.throwing(CLASSNAME, methodName, dex);
            throw dex;
        }

        LOG.log(Level.FINE, "Completed call to TRAX.");
        LOG.exiting(CLASSNAME, methodName);
        return studentInfo;
    }

    /**
     * Transfer the TRAX data from the data value object into a Student object.
     *
     * @param penObj
     * @param traxStudent
     * @return Student
     */
    private Student transferStudentData(
            final PersonalEducationNumber penObj,
            final StudentDemographic traxStudent) {
        final String _m = "transferStudentData(PersonalEducationNumber, StudentDemographic)";
        final Object[] params = {penObj, traxStudent};
        LOG.entering(CLASSNAME, _m, params);

        final StudentImpl student = new StudentImpl();
        student.setPen(penObj);
        student.setFirstName(traxStudent.getFirstName());
        student.setMiddleName(traxStudent.getMiddleName());
        student.setLastName(traxStudent.getLastName());

        LOG.exiting(CLASSNAME, _m);
        return student;
    }

    /**
     * Transfer the TRAX data from the data value object into a School object.
     *
     * @param traxStudent
     * @return School
     */
    private School transferSchoolData(final StudentDemographic traxStudent) {
        final String _m = "transferSchoolData(StudentDemographic)";
        LOG.entering(CLASSNAME, _m, traxStudent);

        String schoolId = traxStudent.getGradMincode();

        if (schoolId.isEmpty()) {
            schoolId = traxStudent.getMincode();
        }

        final SchoolImpl school = new SchoolImpl();

        school.setMincode(schoolId);
        school.setName(traxStudent.getSchoolName());
        school.setSignatureCode(traxStudent.getCertificateSignature());
        school.setTypeIndicator(traxStudent.getSchoolTypeIndicator());

        LOG.exiting(CLASSNAME, _m);
        return school;
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     *
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport englishCertificate(
            final String certType,
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate) throws DomainServiceException {

        CertificateType rsRptType = CertificateType.REGULAR;
        final CertificateSubtype rsRptSubType;

        LOG.log(Level.FINE, "Cert Type: {0}", certType);

        if (certType.equals(Constants.SCCP_CERTIFICATE)) {
            rsRptType = CertificateType.SCCP;
        }

        if (certType.equals(Constants.ENGLISH_DOGWOOD_ADULT)
                || certType.equals(Constants.ENGLISH_DOGWOOD_ADULT_IND)) {
            rsRptType = CertificateType.ADULT;
        }

        if (certType.equals(Constants.ENGLISH_DOGWOOD_IND)
                || certType.equals(Constants.ENGLISH_DOGWOOD_ADULT_IND)) {
            rsRptSubType = CertificateSubtype.INDEPENDENT;
        } else {
            rsRptSubType = CertificateSubtype.DEFAULT;
        }

        LOG.log(Level.FINE, "Type: {0}; Subtype: {1}",
                new Object[]{rsRptType.toString(), rsRptSubType.toString()});

        final GradCertificateReport gradCert = createReport(
                student, school, entityId, certificate,
                CANADA, rsRptType, rsRptSubType);

        return gradCert;
    }

    /**
     * @param certType
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport frenchCertificate(
            final String certType,
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate) throws DomainServiceException {
        final CertificateSubtype rsRptSubType;

        if (Constants.CSF_FRENCH_DOGWOOD.equals(certType)) {
            rsRptSubType = CertificateSubtype.FRANCOPHONE;
        } else {
            rsRptSubType = CertificateSubtype.DEFAULT;
        }

        final GradCertificateReport gradCert = createReport(
                student, school, entityId, certificate,
                CANADA_FRENCH, REGULAR, rsRptSubType);

        return gradCert;
    }

    /**
     * @param student
     * @param school
     * @param entityId
     * @param certificate
     * @param location
     * @param rsRptType
     * @param rsRptSubType
     * @return GradCertificateReport
     * @throws DomainServiceException
     */
    private GradCertificateReport createReport(
            final Student student,
            final School school,
            final String entityId,
            final Certificate certificate,
            final Locale location,
            final CertificateType rsRptType,
            final CertificateSubtype rsRptSubType) throws DomainServiceException {

        final String methodName = "createReport(Student, School, String, Certificate, Locale, CertificateReportType, CertificateReportSubtype)";
        LOG.entering(CLASSNAME, methodName);

        String timestamp = new SimpleDateFormat(DATE_ISO_8601_FULL).format(new Date());

        CertificateReport certificateReport = reportService.createCertificateReport();
        certificateReport.setReportType(rsRptType);
        certificateReport.setReportSubtype(rsRptSubType);
        certificateReport.setLocale(location);
        certificateReport.setStudent(student);
        certificateReport.setCertificate(certificate);
        certificateReport.setSchool(school);

        GradCertificateReport report = null;
        try {
            final ReportDocument rptDoc = reportService.export(certificateReport);

            StringBuilder sb = new StringBuilder("certificate_");
            sb.append(location.toLanguageTag());
            sb.append("_");
            sb.append(entityId);
            sb.append("_");
            sb.append(timestamp);
            sb.append(".");
            sb.append(PDF.getFilenameExtension());
            final String filename = certificateReport.getFilename();

            byte[] inData = rptDoc.asBytes();
            inData = ArrayUtils.nullToEmpty(inData);
            if (ArrayUtils.isEmpty(inData)) {
                String msg = "The generated report output is empty.";
                DomainServiceException dse = new DomainServiceException(null,
                        msg);
                LOG.throwing(CLASSNAME, methodName, dse);
                throw dse;
            }
            byte[] rptData = inData;

            // TODO: Use a constant for the name.
            report = new GradCertificateReportImpl(rptData, PDF, filename, createReportTypeName(rsRptType, rsRptSubType, location));
        } catch (final IOException ex) {
            LOG.log(Level.SEVERE,
                    "Failed to generate the provincial examination report.", ex);
        }

        LOG.exiting(CLASSNAME, methodName);
        return report;
    }

    private String createReportTypeName(
            final CertificateType type,
            final CertificateSubtype subtype,
            final Locale locale) {
        final String reportTypeName
                = type.toString()
                + " "
                + subtype.toString()
                + " "
                + locale.getISO3Language();
        return reportTypeName;
    }
}
