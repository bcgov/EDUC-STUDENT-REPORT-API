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
package ca.bc.gov.educ.isd.traxadaptor.service.impl;

import static ca.bc.gov.educ.isd.common.support.VerifyUtils.trimSafe;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.CourseId;
import ca.bc.gov.educ.isd.traxadaptor.service.TranscriptData;
import ca.bc.gov.educ.isd.traxadaptor.dao.tsw.impl.TswTranNongradEntity;
import static ca.bc.gov.educ.isd.eis.roles.Roles.FULFILLMENT_SERVICES_USER;
import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;
import ca.bc.gov.educ.isd.eis.trax.db.StudentInfo;
import static ca.bc.gov.educ.isd.eis.trax.db.TRAXData.TIMEOUT;
import ca.bc.gov.educ.isd.eis.trax.db.TranscriptCourse;
import ca.bc.gov.educ.isd.traxadaptor.dao.impl.StsTranCourseEntity;
import ca.bc.gov.educ.isd.traxadaptor.impl.StudentInfoImpl;
import ca.bc.gov.educ.isd.traxadaptor.impl.TranscriptCourseImpl;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects that are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. The methods
 * provide transcript information for the student transcript services.
 *
 * The data value objects are created using a JPQL constructor expression where
 * the class constructor is called with the supplied query values. These
 * projected attributes come from 1 or more JPA entities. The outlying
 * attributes which are not directly populated in this manner are individually
 * set by calling additional methods. These attributes may require conditional
 * logic or multiple entity searches to determine the attribute value.
 *
 * @author CGI Information Management Consultants Inc.
 */
@DeclareRoles({TRAX_READ})
public class TranscriptDataBean implements TranscriptData, Serializable {

    private static final long serialVersionUID = 2L;

    private static final String CLASSNAME = TranscriptDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String QUERY_FIND_STUD_BY_PEN
            = "SELECT"
            + "  new " + StudentInfoImpl.class.getCanonicalName() + "("
            + "  a.studNo, a.firstName, a.middleName, a.lastName, a.birthdate,"
            + "  a.localId, a.studGender, a.mincode, a.studGrade, a.gradDate,"
            + "  a.gradReqtYear, a.gradMsgTxt, a.updateDt, a.logoType, b.address1, b.address2,"
            + "  b.city, b.provCode, b.postal, b.traxCountryCode, b.studStatus, b.honourFlag,"
            + "  b.dogwoodFlag, b.prgmCode, b.prgmCode2, b.prgmCode3,"
            + "  b.prgmCode4, b.prgmCode5, c.schlName, c.address1, c.address2,"
            + "  c.city, c.provCode, c.postal, c.phone, c.schlIndType) "
            + "FROM"
            + "  TswTranDemogEntity a, StudentMasterEntity b, TabSchoolEntity c "
            + "WHERE"
            + "  a.studNo = ?1 and a.studNo = b.studNo and a.mincode = c.mincode";

    private static final String QUERY_FIND_COURSES_BY_PEN
            = "SELECT"
            + "  new " + TranscriptCourseImpl.class.getCanonicalName() + "("
            + "  a.primaryKey.studNo, a.courseName, a.primaryKey.crseCode,"
            + "  a.primaryKey.crseLevel, a.primaryKey.crseSession, a.numCredits,"
            + "  a.examPct, a.schoolPct, a.finalPct, a.finalLg, a.interimMark,"
            + "  a.foundationReq, a.specialCase, a.rptCrsType) "
            + "FROM"
            + "  TswTranCourseEntity a "
            + "WHERE"
            + "  a.primaryKey.studNo = ?1";

    private static final String QUERY_FIND_INTERIM_COURSES_BY_PEN
            = "SELECT"
            + "  new " + TranscriptCourseImpl.class.getCanonicalName() + "("
            + "  a.primaryKey.studNo, a.courseName, a.primaryKey.crseCode,"
            + "  a.primaryKey.crseLevel, a.primaryKey.crseSession, a.numCredits,"
            + "  a.examPct, a.schoolPct, a.finalPct, a.finalLg, a.interimMark,"
            + "  a.foundationReq, a.specialCase, a.rptCrsType, a.interimLetterGrade) "
            + "FROM"
            + "  StsTranCourseEntity a "
            + "WHERE"
            + "  a.primaryKey.studNo = ?1";

    private static final String QUERY_COUNT_COURSES_BY_PEN
            = "SELECT"
            + "  COUNT (a) "
            + "FROM"
            + "  TswTranCourseEntity a "
            + "WHERE"
            + "  a.primaryKey.studNo = ?1";

    private final String FORMAT_COURSE_CODE = "%-5s";
    private final String FORMAT_COURSE_LEVEL = "%-3s";

    @PersistenceContext
    private transient EntityManager em;

    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends StudentInfo> findStudentByPEN(final String pen) {
        final String _m = "findStudentByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        final TypedQuery<StudentInfoImpl> query = em.createQuery(QUERY_FIND_STUD_BY_PEN, StudentInfoImpl.class);
        query.setParameter(1, pen);
        query.setHint("javax.persistence.query.timeout", TIMEOUT);
        final List<StudentInfoImpl> students = query.getResultList();
        for (final StudentInfoImpl student : students) {
            determineLastUpdate(student);
            readNonGradReasons(student);
        }
        LOG.exiting(CLASSNAME, _m);
        return students;
    }

    /**
     * @param pen Find a list of courses using the personal education number.
     * @return A list of courses for the student whose PEN matches the given
     * PEN.
     */
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TranscriptCourse> findCoursesByPEN(final String pen) {
        final String _m = "findCoursesByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        final TypedQuery<TranscriptCourseImpl> query = em.createQuery(QUERY_FIND_COURSES_BY_PEN, TranscriptCourseImpl.class);
        query.setParameter(1, pen);
        query.setHint("javax.persistence.query.timeout", TIMEOUT);
        final List<TranscriptCourseImpl> courses = query.getResultList();
        for (final TranscriptCourseImpl course : courses) {
            addExtendedCourseInfo(course);
        }

        LOG.exiting(CLASSNAME, _m);
        return courses;
    }

    /**
     * @param pen Find a list of courses using the personal education number.
     * @return A list of courses for the student whose PEN matches the given
     * PEN.
     */
    @Override
    @RolesAllowed({TRAX_READ, FULFILLMENT_SERVICES_USER})
    public List<? extends TranscriptCourse> findInterimCoursesByPEN(String pen) {
        final String _m = "findCoursesByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        final TypedQuery<TranscriptCourseImpl> query = em.createQuery(QUERY_FIND_INTERIM_COURSES_BY_PEN, TranscriptCourseImpl.class);
        query.setParameter(1, pen);
        query.setHint("javax.persistence.query.timeout", TIMEOUT);
        final List<TranscriptCourseImpl> courses = query.getResultList();
        for (final TranscriptCourseImpl course : courses) {
            addExtendedCourseInfo(course);
        }

        LOG.exiting(CLASSNAME, _m);
        return courses;
    }

    /**
     * @param pen Find a list of courses using the personal education number.
     * @return A list of courses for the student whose PEN matches the given
     * PEN.
     */
    @Override
    @RolesAllowed(TRAX_READ)
    public Integer countCoursesByPEN(final String pen) {
        final String _m = "countCoursesByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        Integer retValue = 0;

        final Query query = em.createQuery(QUERY_COUNT_COURSES_BY_PEN);
        query.setParameter(1, pen);
        query.setHint("javax.persistence.query.timeout", TIMEOUT);
        Object singleResult = query.getSingleResult();

        if (singleResult != null) {
            String stringResult = String.valueOf(singleResult);
            retValue = Integer.valueOf(stringResult);
        }

        LOG.log(Level.FINE, "Found {0} -> {1} transcript courses.",
                new Object[]{String.valueOf(singleResult), retValue});

        LOG.exiting(CLASSNAME, _m);
        return retValue;
    }

    /**
     * Additional information is required to populate all the course fields,
     * found in other entities. Using the provided course information get the
     * entity search parameters, padded out to the correct length as the course
     * attributes are all trimmed. Search for the additional information and
     * populate the course record if that information exists.
     *
     * @param course
     */
    private void addExtendedCourseInfo(final TranscriptCourseImpl course) {
        final String _m = "addExtendedCourseInfo(TranscriptCourseImpl)";
        LOG.entering(CLASSNAME, _m);

        final String pen = course.getPen();
        final String courseCode = course.getCourseCode();
        final String paddedCourseCode = String.format(FORMAT_COURSE_CODE, courseCode);
        final String courseLevel = course.getCourseLevel();
        final String paddedCourseLevel = String.format(FORMAT_COURSE_LEVEL, courseLevel);
        final String sessionDate = course.getSessionDate();

        final Character usedForGrad;

        if (course.isExaminable()) {
            usedForGrad = getPEUsedForGrad(pen, paddedCourseCode, paddedCourseLevel, sessionDate);
        } else {
            usedForGrad = getSXRelatedUsedForGrad(pen, paddedCourseCode, paddedCourseLevel, sessionDate, course);
        }

        // Converts the character to a string, then trims to remove spaces.
        course.setUsedForGrad(trimSafe(usedForGrad));

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * Search the <code>StSTranCourseEntity</code> looking for the matching
     * student number, course code, course level and course session as the
     * provided course.
     *
     * This information is used to determine if there is a
     * <code>Used_For_Grad</code> value associated with this course. It is also
     * used to set the related course code and related course level for this
     * course if a matching record is found.
     *
     * @param pen The students Personal Education Number
     * @param paddedCourseCode The course short code
     * @param paddedCourseLevel The corse grade level
     * @param session Course session the student attended
     * @param course Transcript Course object to be populated with related
     * course and level data.
     * @return Used_For_Grad Character if one is found
     */
    private Character getSXRelatedUsedForGrad(
            final String pen,
            final String paddedCourseCode,
            final String paddedCourseLevel,
            final String session,
            final TranscriptCourseImpl course) {
        final String _m = "getSXRelatedUsedForGrad(String, String, String, String, TranscriptCourseImpl)";
        LOG.entering(CLASSNAME, _m);

        Character usedForGraduation = ' ';

        final CourseId courseID = new CourseId(pen, paddedCourseCode, paddedCourseLevel, session);
        final Query query = em.createNamedQuery("StsTranCourseEntity.findByKey");

        query.setParameter("primaryKey", courseID);

        try {
            final StsTranCourseEntity stsTranCourseEntity = (StsTranCourseEntity) query.getSingleResult();

            final String relatedCourse = stsTranCourseEntity.getRelatedCrse();
            final String relatedCourseSanitized = trimSafe(relatedCourse);
            course.setRelatedCourse(relatedCourseSanitized);

            final String relatedLevel = stsTranCourseEntity.getRelatedLevel();
            final String relatedLevelSanitized = trimSafe(relatedLevel);
            course.setRelatedLevel(relatedLevelSanitized);

            final Character usedForGraduationValue = stsTranCourseEntity.getUsedForGrad();
            usedForGraduation = (usedForGraduationValue == null ? ' ' : usedForGraduationValue);
        } catch (final NonUniqueResultException ex) {
            // this indicates a data issue.  Unable to determine correct record so leave TranscriptCourseImpl with default empty attributes.
            LOG.log(Level.WARNING, "Found multiple StudXcrseEntities matching {0},{1},{2},{3}", new Object[]{pen, paddedCourseCode, paddedCourseLevel, session});
        } catch (final NoResultException ex) {
            // Do nothing, this is a valid scenario.
            // Leave TranscriptCourseImpl with default empty attributes.
            LOG.log(Level.FINE, "Student has no StudXcrseEntity: " + pen, ex.getMessage());
        }

        LOG.exiting(CLASSNAME, _m);
        return usedForGraduation;
    }

    /**
     * Search the <code>StsTranCourseEntity</code> looking for the matching
     * student number, course code, course level and course session as the
     * provided course. This information is used to determine if there is a
     * <code>Used_For_Grad</code> value associated with this course.
     *
     * @param pen
     * @param paddedCourseCode
     * @param paddedCourseLevel
     * @param session
     * @return Used_For_Grad Character if one is found
     */
    private Character getPEUsedForGrad(
            final String pen,
            final String paddedCourseCode,
            final String paddedCourseLevel,
            final String session) {
        final String _m = "getPEUsedForGrad(String, String, String, String)";
        LOG.entering(CLASSNAME, _m);
        Level logLevel = Level.FINE;
        String logString;

        Character usedForGraduation = ' ';

        // some value passed in are dirty which prevents from finding the related
        // entity.
        final String sanitizedPen = trimSafe(pen);
        final String sanitizedCourseCode = trimSafe(paddedCourseCode);
        final String sanitizedCoursedLevel = trimSafe(paddedCourseLevel);
        final String sanitizedSession = trimSafe(session);

        CourseId courseID = new CourseId(sanitizedPen, sanitizedCourseCode, sanitizedCoursedLevel, sanitizedSession);
        Query query = em.createNamedQuery("StsTranCourseEntity.findByKey");

        query.setParameter("primaryKey", courseID);
        try {
            final StsTranCourseEntity stsTransCourseEntity = (StsTranCourseEntity) query.getSingleResult();
            final Character usedForGraduationVal = stsTransCourseEntity.getUsedForGrad();

            usedForGraduation = (usedForGraduationVal == null ? ' ' : usedForGraduationVal);

            logString = buildLogString("For pen >{0}<, crseCode >{1}<, crseLevel >{2}<, session >{3}<: usedForGrad >{4}<",
                    sanitizedPen,
                    sanitizedCourseCode,
                    sanitizedCoursedLevel,
                    sanitizedSession,
                    usedForGraduation);
        } catch (final NonUniqueResultException ex) {
            // this indicates a data issue.  Unable to determine correct record.
            logLevel = Level.WARNING;
            logString = buildLogString("Found multiple StsTranCourseEntity matching {0},{1},{2},{3}",
                    sanitizedPen,
                    sanitizedCourseCode,
                    sanitizedCoursedLevel,
                    sanitizedSession);
        } catch (final NoResultException ex) {
            // Do nothing for a student without exam entities.
            logString = buildLogString("Student has no STs Transcript Course entity for pen {0}", sanitizedPen);
        }

        LOG.log(logLevel, logString);

        LOG.exiting(CLASSNAME, _m);
        return usedForGraduation;
    }

    /**
     * Search all the transcript courses associated with the given PEN and
     * select the most recent update date (UTG update).
     *
     * @param student
     */
    private void determineLastUpdate(final StudentInfoImpl student) {
        final String _m = "determineLastUpdate(StudentInfoImpl)";
        LOG.entering(CLASSNAME, _m);

        final String pen = student.getPen();
        final Query query = em.createNamedQuery("TswTranCourseEntity.findMaxUpdtByStudNo");
        query.setParameter("studNo", pen);
        try {
            final Long lastUpdtVal = (Long) query.getSingleResult();
            student.setLastUpdateDate(lastUpdtVal);
        } catch (final NonUniqueResultException ex) {
            // this indicates a data issue as the query should return a single data value
            LOG.log(Level.SEVERE, "Found multiple results for query TswTranCourseEntity.findMaxUpdtByStudNo which should return a single value");
        } catch (final NoResultException ex) {
            // Do nothing for a student without courses.
            LOG.log(Level.FINE, "Student has no courses: " + pen, ex.getMessage());
        }

        LOG.exiting(CLASSNAME, _m);
    }

    /**
     * query for any existing reasons why the student did not meet graduation
     * requirements.
     *
     * @param student
     */
    @SuppressWarnings("unchecked")
    private void readNonGradReasons(final StudentInfoImpl student) {
        String _m = "readNonGradReasons(StudentInfoImpl)";
        LOG.entering(CLASSNAME, _m);

        final String pen = student.getPen();
        final Query query = em.createNamedQuery("TswTranNongradEntity.findByStudNo", TswTranNongradEntity.class);
        query.setParameter("studNo", pen);
        final List<TswTranNongradEntity> reasonList = query.getResultList();
        final HashMap<String, String> reasons = new HashMap<>();
        for (final TswTranNongradEntity reason : reasonList) {
            final String code = reason.getNonGradCode().trim();
            final String text = reason.getNonGradDesc().trim();
            reasons.put(code, text);
        }
        student.setNonGradReasons(reasons);

        LOG.exiting(CLASSNAME, _m);
    }

    private String buildLogString(final String logString, final Object... objects) {
        return MessageFormat.format(logString, objects);
    }
}
