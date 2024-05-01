package myproject.utility.data_importer.base;

import myproject.import_data.dto.ImportResultDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface DataImporter {
    List<Map<String, String>> loadData(InputStream input) throws IOException;
    ImportResultDTO importData(List<Map<String, String>> data);
}
