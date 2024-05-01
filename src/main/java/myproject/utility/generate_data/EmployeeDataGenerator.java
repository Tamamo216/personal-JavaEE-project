package myproject.utility.generate_data;

import myproject.utility.data_importer.base.DataImporter;
import myproject.utility.data_importer.employee.csv.EmployeeCSVImporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EmployeeDataGenerator {
    private static final String[] HEADERS = {"date_of_birth", "first_name", "middle_name", "last_name", "gender", "salary", "deptid"};
    private static final int MIN_SALARY = 0;
    private static final int MAX_SALARY = 1000000;
    private static final int MAX_DEPT_ID = 9;
    private static final int RECORD_NUM = 1000000;

    public static void generateOneMillionsEmployees(InputStream initialDataFile) throws IOException {
        DataImporter dataImporter = new EmployeeCSVImporter();
        List<Map<String, String>> initialData = dataImporter.loadData(initialDataFile);
        List<String> availableDateOfBirth = new ArrayList<>();
        List<String> availableFirstName = new ArrayList<>();
        List<String> availableMiddleName = new ArrayList<>();
        List<String> availableLastName = new ArrayList<>();
        List<String> availableGender = new ArrayList<>(Arrays.asList("MALE", "FEMALE"));
        initialData.forEach(row -> {
            availableDateOfBirth.add(row.get("date_of_birth"));
            availableFirstName.add(row.get("first_name"));
            availableMiddleName.add(row.get("middle_name"));
            availableLastName.add(row.get("last_name"));
        });

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).build();
        String resourcePath = Paths.get(".").toAbsolutePath().normalize().toString();
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(resourcePath, "one_million_rows_employees.csv"));
                CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for (int i = 0; i < RECORD_NUM; ++i) {
                Random random = new Random();
                csvPrinter.printRecord(
                        availableDateOfBirth.get(random.nextInt(availableDateOfBirth.size()-1)),
                        availableFirstName.get(random.nextInt(availableFirstName.size()-1)),
                        availableMiddleName.get(random.nextInt(availableMiddleName.size()-1)),
                        availableLastName.get(random.nextInt(availableLastName.size()-1)),
                        availableGender.get(random.nextInt(availableGender.size()-1)),
                        random.nextInt(MAX_SALARY - MIN_SALARY) + MIN_SALARY,
                        random.nextInt(MAX_DEPT_ID)
                );
            }
        }

    }
}
