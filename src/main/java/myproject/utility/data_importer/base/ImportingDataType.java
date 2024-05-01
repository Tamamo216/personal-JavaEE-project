package myproject.utility.data_importer.base;

public enum ImportingDataType {
    CSV,
    EXCEL;

    public static ImportingDataType parseContentType(String value) {
        if (value.contains("csv"))
            return CSV;
        return EXCEL;
    }
}
