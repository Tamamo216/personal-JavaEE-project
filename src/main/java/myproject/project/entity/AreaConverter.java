package myproject.project.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;


@Converter
public class AreaConverter implements AttributeConverter<Area, String> {

    @Override
    public String convertToDatabaseColumn(Area area) {
//        return switch (area) {
//            case BANKING -> "Banking";
//            case HEALTHCARE -> "Healthcare";
//            case FINTECH -> "Fintech";
//            case IOT -> "IOT";
//            case FINANCE -> "Finance";
//            case HUMAN_RESOURCES -> "Human Resources";
//            case MARKETING -> "Marketing";
//            case OPERATIONS -> "Operations";
//            case RESEARCH_DEVELOPMENT -> "Research & Development";
//        };
        return area.toString();
    }

    @Override
    public Area convertToEntityAttribute(String s) {
//        return switch (s) {
//            case "Banking" -> Area.BANKING;
//            case "Healthcare" -> Area.HEALTHCARE;
//            case "Fintech" -> Area.FINTECH;
//            case "IOT" -> Area.IOT;
//            case "Finance" -> Area.FINANCE ;
//            case "Human Resources" -> Area.HUMAN_RESOURCES;
//            case "Marketing" -> Area.MARKETING;
//            case "Operations" -> Area.OPERATIONS;
//            case "Research & Development" -> Area.RESEARCH_DEVELOPMENT;
//            default -> null;
//        };
        return Stream.of(Area.values())
                .filter(area -> area.toString().equals(s))
                .findFirst().orElse(null);
    }
}
