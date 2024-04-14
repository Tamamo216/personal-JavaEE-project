package myproject.utility.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVUtils {

    public static Iterable<CSVRecord> readCSV(InputStream input) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().builder()
                .setHeader()
                .setSkipHeaderRecord(false)
                .build();

        return csvFormat.parse(new InputStreamReader(input));
    }
}
