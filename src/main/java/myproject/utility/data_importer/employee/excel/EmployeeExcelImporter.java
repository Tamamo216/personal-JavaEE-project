package myproject.utility.data_importer.employee.excel;

import lombok.NoArgsConstructor;
import myproject.department.dao.DepartmentDAO;
import myproject.department.entity.Department;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.entity.Gender;
import myproject.import_data.dto.ImportResultDTO;
import myproject.utility.data_importer.base.DataImporter;
import myproject.utility.data_importer.base.ImportingDataType;
import myproject.utility.data_importer.employee.base.EmployeeDataKey;
import myproject.utility.data_importer.employee.base.EmployeeImporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@RequestScoped
@EmployeeImporter(dataType = ImportingDataType.EXCEL)
public class EmployeeExcelImporter implements DataImporter {
    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;
    @Inject
    public EmployeeExcelImporter(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Map<String, String>> loadData(InputStream input) throws IOException {
        List<Map<String, String>> data = null;
        try (Workbook workbook = WorkbookFactory.create(new BufferedInputStream(input))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() < 1)
                    continue;
                Map<String, String> employeeMap = new HashMap<>();
                for (Cell cell : row) {
                    int cellIdx = cell.getColumnIndex();
                    switch (cellIdx) {
                        case 1:
                            employeeMap.put(EmployeeDataKey.DATE_OF_BIRTH, extractCellValue(cell));
                            break;
                        case 2:
                            employeeMap.put(EmployeeDataKey.FIRST_NAME, extractCellValue(cell));
                            break;
                        case 3:
                            employeeMap.put(EmployeeDataKey.MIDDLE_NAME, extractCellValue(cell));
                            break;
                        case 4:
                            employeeMap.put(EmployeeDataKey.LAST_NAME, extractCellValue(cell));
                            break;
                        case 5:
                            employeeMap.put(EmployeeDataKey.GENDER, extractCellValue(cell));
                            break;
                        case 6:
                            employeeMap.put(EmployeeDataKey.SALARY, extractCellValue(cell));
                        case 7:
                            employeeMap.put(EmployeeDataKey.DEPARTMENT_ID, extractCellValue(cell));
                    }
                }
                data.add(employeeMap);
            }
        }
        return data;
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
                            .firstName(row.get(EmployeeDataKey.FIRST_NAME))
                            .middleName((row.get(EmployeeDataKey.MIDDLE_NAME)))
                            .lastName(row.get(EmployeeDataKey.LAST_NAME))
                            .gender(Gender.valueOf(row.get(EmployeeDataKey.GENDER).toUpperCase()))
                            .salary(Long.parseLong(row.get(EmployeeDataKey.SALARY)))
                            .department(departmentMap.get(Long.parseLong(row.get(EmployeeDataKey.DEPARTMENT_ID))))
                            .build();
                    return employee; })
                .toList();

        employeeDAO.batchInsert(employees);
        return ImportResultDTO.builder().message("Import successfully").build();
    }

    private String extractCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
    }
}
