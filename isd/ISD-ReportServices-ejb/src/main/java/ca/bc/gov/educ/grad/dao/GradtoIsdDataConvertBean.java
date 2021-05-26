package ca.bc.gov.educ.grad.dao;

import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.isd.eis.trax.db.StudentDemographic;
import ca.bc.gov.educ.isd.eis.trax.db.StudentInfo;
import ca.bc.gov.educ.isd.eis.trax.db.TranscriptCourse;
import ca.bc.gov.educ.isd.school.School;
import ca.bc.gov.educ.isd.student.Student;
import ca.bc.gov.educ.isd.transcript.Transcript;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.StsTranCourseEntity;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranNongradEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradtoIsdDataConvertBean {

    public Student getStudent(ReportData reportData) {
        return null;
    }

    public StudentInfo getStudentInfo(ReportData reportData) {
        return null;
    }

    public StudentDemographic  getStudentDemog(ReportData reportData) {
        return null;
    }

    public Transcript getTranscript(ReportData reportData) {
        return null;
    }

    public School getSchool(ReportData reportData) {
        return null;
    }

    public List<TranscriptCourse> getTranscriptCources(ReportData reportData) {
        return null;
    }

    public StsTranCourseEntity getStsTranCourse(ReportData reportData) {
        return null;
    }

    public List<TswTranNongradEntity> getTswTranNongradEntity(ReportData reportData) {
        return null;
    }
}
