package ca.bc.gov.educ.grad.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class StudentInformation {

    // basic student info
    private String pen;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Date birthDate = new Date();

    // student transcript info
    private String schoolId = "";
    private Date reportDate = new Date(0L);
    private Date lastUpdateDate = new Date(0L);
    private String logo = "";
    private Character gender = ' ';
    private Character status = ' ';
    private Boolean honourFlag = Boolean.FALSE;
    private Boolean dogwoodFlag = Boolean.FALSE;
    private String grade = "";
    private Date gradDate = new Date(0L);
    private String gradProgram = "";
    private final List<String> academicProgram = new ArrayList<>();
    private Map<String, String> nonGradReasons = new HashMap<>();
    private String gradMessage = "";

    // student address
    private String studentAddress1 = "";
    private String studentAddress2 = "";
    private String studentCity = "";
    private String studentProv = "";
    private String studentPostalCode = "";
    private String traxStudentCountry = "";
    private String isoStudentCountry;

    // school information
    private String mincode = "";
    private String schoolName = "";
    private String schoolStreet = "";
    private String schoolStreet2 = "";
    private String schoolCity = "";
    private String schoolPostalCode = "";
    private String schoolProv = "";
    private String schoolPhone = "";
    private String schoolTypeIndicator = "";
    private String schoolTypeBanner = "";
}
