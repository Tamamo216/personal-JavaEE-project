package myproject.utility.data_importer.employee.csv;

import lombok.NoArgsConstructor;
import myproject.department.dao.DepartmentDAO;
import myproject.department.entity.Department;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.entity.Gender;
import myproject.import_data.dto.ImportResultDTO;
import myproject.utility.data_importer.base.DataImporter;
import myproject.utility.data_importer.base.ImportingDataType;
import myproject.utility.data_importer.employee.base.EmployeeImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@NoArgsConstructor
@RequestScoped
@EmployeeImporter(dataType = ImportingDataType.CSV)
public class EmployeeCSVImporter implements DataImporter {
    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;

    @Inject
    public EmployeeCSVImporter(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Map<String, String>> loadData(InputStream input) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().builder()
                .setHeader()
                .setSkipHeaderRecord(false)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(new InputStreamReader(input));
        return StreamSupport.stream(records.spliterator(), false)
                .map(row -> row.toMap()).toList();
    }

    @Override
    public ImportResultDTO importData(List<Map<String, String>> data) {
        Map<Long, Department> departmentMap = new HashMap<>();
        departmentDAO.getSortedDepartments(true).forEach(department -> {
            departmentMap.put(department.getId(), department);
        });
        List<Employee> employees = data.stream()
                .filter(row -> row.get("gender").equals("Male") || row.get("gender").equals("Female"))
                .map(row -> {
                    List<Integer> date = Arrays.stream(row.get("date_of_birth").split("-"))
                            .map(dateStr -> Integer.parseInt(dateStr))
                            .toList();
                    Employee employee = Employee.builder()
                            .dateOfBirth(LocalDate.of(date.get(0), date.get(1), date.get(2)))
                            .firstName(row.get("first_name"))
                            .middleName((row.get("middle_name")))
                            .lastName(row.get("last_name"))
                            .gender(Gender.valueOf(row.get("gender").toUpperCase()))
                            .salary(Long.parseLong(row.get("salary")))
                            .department(departmentMap.get(Long.parseLong(row.get("deptid"))))
                            .build();
                    return employee; })
                .toList();

        employeeDAO.batchInsert(employees);
        return ImportResultDTO.builder().message("Import successfully").build();
    }
}
