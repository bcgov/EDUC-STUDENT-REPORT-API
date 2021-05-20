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
 *  File:                $Id::                                           $
 *  Date of Last Commit: $Date::                                         $
 *  Revision Number:     $Rev::                                          $
 *  Last Commit by:      $Author::                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.traxadaptor.dao.impl;

import static ca.bc.gov.educ.isd.eis.common.DatabaseConstants.*;
import ca.bc.gov.educ.isd.eis.trax.db.SchoolMaster;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An auto generated entity for the SCHOOL_MASTER_VW view which is a view
 * created in the ISD database and linked to the SPM database school_master
 * table using the database link. The view definition is as follows:
 *
 * <code>
 * CREATE OR REPLACE FORCE VIEW "ISD"."SCHOOL_MASTER_VW" ("MINCODE", "DISTNO",
 * "SCHLNO", "SCHOOL_CATEGORY_CODE") AS (select (concat(distno,schlno)) mincode,
 * distno, schlno, school_category_code from school_master@sldclink.world) with
 * read only;
 * </code>
 *
 * JPA access is on attributes directly. The attributes in this list are used to
 * fulfill the data requests for the Student Demographics service.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SchoolMasterEntity implements SchoolMaster {

    private static final long serialVersionUID = 1L;

    private String mincode;
    private String distno;
    private String schlno;
    private String schoolCategoryCode;

    public SchoolMasterEntity() {
    }

    @Override
    public String getMincode() {
        return this.mincode;
    }

    @Override
    public String getDistno() {
        return this.distno;
    }

    @Override
    public String getSchlno() {
        return this.schlno;
    }

    @Override
    public String getSchoolCategoryCode() {
        return this.schoolCategoryCode;
    }
}
