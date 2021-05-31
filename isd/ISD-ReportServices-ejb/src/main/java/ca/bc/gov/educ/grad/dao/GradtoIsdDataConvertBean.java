package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.isd.eis.trax.db.*;
import ca.bc.gov.educ.isd.grad.NonGradReason;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.transcript.Transcript;
import ca.bc.gov.educ.isd.transcript.TranscriptResult;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.*;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranDemogEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranNongradEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranNongradEntityPK;
import ca.bc.gov.educ.isd.traxadaptor.impl.StudentDemographicImpl;
import ca.bc.gov.educ.isd.traxadaptor.impl.StudentInfoImpl;
import ca.bc.gov.educ.isd.traxadaptor.impl.TranscriptCourseImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GradtoIsdDataConvertBean {

    public StudentInfo getStudentInfo(ReportData reportData) {
        Student student = getStudent(reportData);
        School school = getSchool(reportData);
        StudentInfoImpl result = new StudentInfoImpl(
            student.getPen().getValue(),// String studNo,
            student.getFirstName(),// String firstName,
            student.getMiddleName(),// String middleName,
            student.getLastName(),// String lastName,
            student.getBirthdate().getTime(),// Long birthdate,
            student.getEntityId(),// String localId,
            null,// Character studGender,
            null,// String mincode,
            student.getGrade(),// String studGrade,
            null,// String gradDate,
            reportData.getGradProgram().getCode().getCode(),// String gradReqtYear,
            reportData.getGradMessage(),// String gradMessage,
            reportData.getUpdateDate().getTime(),// Long updateDt,
            null,// String logoType,
            student.getCurrentMailingAddress().getStreetLine1(),// String studentAddress1,
            student.getCurrentMailingAddress().getStreetLine2(),// String studentAddress2,
            student.getCurrentMailingAddress().getCity(),// String studentCity,
            student.getCurrentMailingAddress().getRegion(),// String studentProv,
            student.getCurrentMailingAddress().getPostalCode(),// String studentPostalCode,
            student.getCurrentMailingAddress().getCountryCode(),// String traxStudentCountry,
            null,// Character studStatus,
            null,// Character honourFlag,
            null,// Character dogwoodFlag,
            null,// String prgmCode,
            null,// String prgmCode2,
            null,// String prgmCode3,
            null,// String prgmCode4,
            null,// String prgmCode5,
            school.getName(),// String schoolName,
            school.getPostalAddress().getStreetLine1(),// String schoolStreet,
            school.getPostalAddress().getStreetLine2(),// String schoolStreet2,
            school.getPostalAddress().getCity(),// String schoolCity,
            school.getPostalAddress().getRegion(),// String schoolProv,
            school.getPostalAddress().getPostalCode(),// String schoolPostalCode,
            null,// String schoolPhone,
            school.getTypeIndicator()// Character schlIndType
        );
        return result;
    }

    public List<StudentDemographic>  getStudentDemog(ReportData reportData) {
        StudentMaster studentMaster = getStudentMaster(reportData);
        TabSchool tabSchool = getTabSchool(reportData);

        List<StudentDemographic> result = new ArrayList<>();
        StudentDemographicImpl studentDemographic = new StudentDemographicImpl(
                studentMaster,
                tabSchool
        );

        result.add(studentDemographic);
        return result;
    }

    public Transcript getTranscript(ReportData reportData) {
        return reportData.getTranscript();
    }

    public Student getStudent(ReportData reportData) {
        return reportData.getStudent();
    }

    public School getSchool(ReportData reportData) {
        return reportData.getSchool();
    }

    public List<TranscriptCourse> getTranscriptCources(ReportData reportData) {
        Student student = getStudent(reportData);
        List<TranscriptCourse> result = new ArrayList<>();
        for(TranscriptResult r: reportData.getTranscript().getResults()) {
            TranscriptCourseImpl course = new TranscriptCourseImpl(
                    student.getPen().getValue(), //String pen,
                    r.getCourse().getName(), //String courseName,
                    r.getCourse().getCode(), //String crseCode,
                    r.getCourse().getLevel(), //String crseLevel,
                    r.getCourse().getSessionDate(), //String sessionDate,
                    r.getCourse().getCredits(), //String credits,
                    r.getMark().getExamPercent(), //String examPercent,
                    r.getMark().getSchoolPercent(), //String schoolPercent,
                    r.getMark().getFinalPercent(), //String finalPercent,
                    r.getMark().getFinalLetterGrade(), //String finalLetterGrade,
                    r.getMark().getInterimPercent(), //String interimMark,
          null, //String requirement,
          null, //String specialCase,
                    r.getCourse().getType() //Character courseType
            );
            result.add(course);
        }
        return result;
    }

    public StsTranCourseEntity getStsTranCourse(ReportData reportData) {
        return null;
    }

    public List<TswTranNongradEntity> getTswTranNongradEntity(ReportData reportData) {
        List<TswTranNongradEntity> result = new ArrayList<>();
        for(NonGradReason reason: reportData.getNonGradReasons()) {
            TswTranNongradEntityPK pk = new TswTranNongradEntityPK(
                    reportData.getStudent().getPen().getValue(),
                    reason.getCode()
            );
            TswTranNongradEntity entity = new TswTranNongradEntity(
                    pk,
                    reason.getDescription(),
                    null
            );
            result.add(entity);
        }
        return result;
    }

    public Character getSchoolEligibility(ReportData reportData) {
        return 'Y';
    }

    public TswTranDemogEntity getTswTranDemog(ReportData reportData) {
        Student student = getStudent(reportData);
        School school = getSchool(reportData);
        TswTranDemogEntity result = new TswTranDemogEntity(
                student.getPen().getValue(), //String studNo,
                student.getFirstName(), //String firstName,
                student.getMiddleName(), //String middleName,
                student.getLastName(), //String lastName,
                student.getBirthdate().getTime(), //Long birthdate,
                student.getEntityId(), //String localId,
                null, //Character studGender,
                school.getMinistryCode(), //String mincode,
                student.getGrade(), //String studGrade,
                null, //String gradDate,
                null, //String gradReqtYear,
                reportData.getUpdateDate().getTime(), //Long updateDt,
                null, //String logoType,
                reportData.getGradMessage(), //String gradMsgTxt,
                null, //Character gradFlag,
                null  //Character currentFormerFlag
        );
        return result;
    }

    public SchoolMasterEntity getSchoolMaster(ReportData reportData) {
        School school = getSchool(reportData);
        SchoolMasterEntity result = new SchoolMasterEntity(
                school.getMinistryCode(), //String mincode,
                null, //String distno,
                null, //String schlno,
                null  //String schoolCategoryCode
        );
        return result;
    }

    public TabSchoolEntity getTabSchool(ReportData reportData) {
        School school = getSchool(reportData);
        TabSchoolEntity result = new TabSchoolEntity(
                school.getMinistryCode(), //String mincode,
                school.getName(), //String schlName,
                school.getPostalAddress().getStreetLine1(), //String address1,
                school.getPostalAddress().getStreetLine2(), //String address2,
                school.getPostalAddress().getCity(), //String city,
                school.getPostalAddress().getRegion(), //String provCode,
                school.getPostalAddress().getPostalCode(), //String postal,
                null, //String signatureDistno,
                null, //Character xcriptElig,
                null, //String phone,
                null, //Character schlIndType
                null //Character dogwoodElig
        );
        return result;
    }

    public StudentMaster getStudentMaster(ReportData reportData) {
        StudentInfo studentInfo = getStudentInfo(reportData);
        StudentMasterEntity result = new StudentMasterEntity(
                studentInfo.getPen(), //String studNo,
                studentInfo.getFirstName(), //String studGiven,
                studentInfo.getMiddleName(), //String studMiddle,
                studentInfo.getLastName(), //String studSurname,
                studentInfo.getBirthDate().toString(), //String studBirth,
                studentInfo.getStudentAddress1(), //String address1,
                studentInfo.getStudentAddress2(), //String address2,
                studentInfo.getStudentCity(), //String city,
                studentInfo.getStudentProv(), //String provCode,
                studentInfo.getStudentPostalCode(), //String postal,
                studentInfo.getStudentStatus(), //Character studStatus,
                studentInfo.getGrade(), //String studGrade,
                studentInfo.getGradDate().getTime(), //Long gradDate,
                studentInfo.getGraduationProgramCode().getCode(), //String gradReqtYear,
                null, //Character honourFlag,
                null, //Character dogwoodFlag,
                null, //Long sccDate,
                studentInfo.getMincode(), //String mincode,
                null, //String mincodeGrad,
                null, //String prgmCode,
                null, //String prgmCode2,
                null, //String prgmCode3,
                null, //String prgmCode4,
                null, //String prgmCode5,
                null, //String englishCert,
                null, //String frenchCert,
                null, //String traxCountryCode,
                null, //String stud_true_no,
                null  //String isoCountryCode
        );
        return result;
    }

    public List<StudentProfileMasterLite> getStudentProfileMaster(ReportData reportData) {
        Student student = getStudent(reportData);
        List<StudentProfileMasterLite> result = new ArrayList<>();

        StudentProfileMasterLiteEntity entity = new StudentProfileMasterLiteEntity();
        entity.setBirthdate(student.getBirthdate().toString());
        entity.setFirstName(student.getFirstName());
        entity.setMiddleName(student.getMiddleName());
        entity.setLastName(student.getLastName());
        entity.setPen(student.getPen().getValue());

        result.add(entity);

        return result;
    }
}
