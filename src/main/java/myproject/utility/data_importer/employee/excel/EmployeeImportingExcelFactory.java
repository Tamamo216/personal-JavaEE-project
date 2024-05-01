package myproject.utility.data_importer.employee.excel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import myproject.utility.data_importer.base.DataImporter;
import myproject.utility.data_importer.base.DataImportingFactory;
import myproject.utility.data_importer.base.ImportingDataType;
import myproject.utility.data_importer.employee.base.EmployeeImporter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@AllArgsConstructor
@NoArgsConstructor
@RequestScoped
public class EmployeeImportingExcelFactory extends DataImportingFactory {
    @Inject
    @EmployeeImporter(dataType = ImportingDataType.EXCEL)
    private DataImporter dataImporter;

    @Override
    protected DataImporter getImporter() {
        return dataImporter;
    }
}
