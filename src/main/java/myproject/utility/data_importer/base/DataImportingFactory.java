package myproject.utility.data_importer.base;

import myproject.import_data.dto.ImportResultDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class DataImportingFactory {
    public ImportResultDTO importData(InputStream input) throws IOException {
        DataImporter dataImporter = getImporter();
        List<Map<String, String>> data = dataImporter.loadData(input);
        return dataImporter.importData(data);
    }

    protected abstract DataImporter getImporter();
}
