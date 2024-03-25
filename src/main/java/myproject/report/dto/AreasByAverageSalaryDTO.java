package myproject.report.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.project.entity.Area;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AreasByAverageSalaryDTO {
    private String area;
    private Double averageSalary;

    public AreasByAverageSalaryDTO(Area area, Double averageSalary) {
        this.area = area.toString();
        this.averageSalary = averageSalary;
    }
}
