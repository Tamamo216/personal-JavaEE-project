package myproject.project.entity;

public enum Area {
    BANKING("Banking"),
    HEALTHCARE("Healthcare"),
    FINTECH("Fintech"),
    IOT("IOT"),
    FINANCE("Finance"),
    HUMAN_RESOURCES("Human Resources"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    RESEARCH_DEVELOPMENT("Research and Development");

    private final String label;
    private Area(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
