package myproject.import_data.service;

import myproject.department.dao.DepartmentDAO;
import myproject.department.entity.Department;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.entity.Gender;
import myproject.import_data.dto.ImportResultDTO;
import myproject.utility.csv.CSVUtils;
import org.apache.commons.csv.CSVRecord;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Stateless
public class ImportDataService {
    @Inject
    public DepartmentDAO departmentDAO;
    @Inject
    public EmployeeDAO employeeDAO;

    public ImportResultDTO insertEmployeeData(InputStream data, String extension) throws IOException {
        if (extension.equals("csv")) {
            Iterable<CSVRecord> records = CSVUtils.readCSV(data);
            Map<Long, Department> departmentMap = new HashMap<>();
            departmentDAO.getSortedDepartments(true).forEach(department -> {
                departmentMap.put(department.getId(), department);
            });
            List<Employee> employees = StreamSupport.stream(records.spliterator(), false)
                    .map(row -> {
                        List<Integer> date = Arrays.stream(row.get("date_of_birth").split("-"))
                                .map(dateStr -> Integer.parseInt(dateStr))
                                .toList();
                        Employee employee = Employee.builder()
                            .dateOfBirth(LocalDate.of(date.get(2), date.get(1), date.get(0)))
                            .firstName(row.get("first_name"))
                            .middleName((row.get("middle_name")))
                            .lastName(row.get("last_name"))
                            .gender(Gender.valueOf(row.get("gender")))
                            .salary(Long.parseLong(row.get("salary")))
                            .department(departmentMap.get(Long.parseLong(row.get("deptid"))))
                            .build();
                        return employee; })
                    .toList();
            employeeDAO.batchInsert(employees);
        }
        return ImportResultDTO.builder().message("Import successfully").build();
    }
}
