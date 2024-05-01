package myproject.import_data.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import myproject.department.dao.DepartmentDAO;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.import_data.dto.ImportResultDTO;
import myproject.utility.data_importer.base.DataImportingFactory;
import myproject.utility.data_importer.base.ImportingDataType;
import myproject.utility.data_importer.employee.csv.EmployeeImportingCSVFactory;
import myproject.utility.data_importer.employee.excel.EmployeeImportingExcelFactory;
import myproject.utility.generate_data.EmployeeDataGenerator;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class ImportDataService {
    @Inject
    private DepartmentDAO departmentDAO;
    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    @Any
    private Instance<DataImportingFactory> importingFactories;

    public ImportResultDTO insertEmployeeData(InputStream data, ImportingDataType dataType) throws IOException {
        DataImportingFactory importingFactory = null;
        switch (dataType) {
            case CSV:
                importingFactory = importingFactories.select(EmployeeImportingCSVFactory.class).get();
                break;
            case EXCEL:
                importingFactory = importingFactories.select(EmployeeImportingExcelFactory.class).get();
            default: break;
        }
        return importingFactory.importData(data);

    }

    public ImportResultDTO generateMillionEmployeeData(InputStream initDataFile) throws IOException {
        EmployeeDataGenerator.generateOneMillionsEmployees(initDataFile);
        return ImportResultDTO.builder().message("Generated successfully").build();
    }
    
    public ImportResultDTO insertMillionEmployees() throws IOException {
        InputStream dataFile = this.getClass().getResourceAsStream("/one_million_rows_employees.csv");
        DataImportingFactory importingFactory = new EmployeeImportingCSVFactory();
        return importingFactory.importData(dataFile);
    }
}
