package myproject.utility.data_importer.employee.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDataKey {
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    public static final String GENDER = "gender";
    public static final String SALARY = "salary";
    public static final String DEPARTMENT_ID = "departmentId";
}
