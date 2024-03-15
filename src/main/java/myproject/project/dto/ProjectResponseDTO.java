package myproject.project.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import myproject.department.dto.DepartmentResponseDTO;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProjectResponseDTO {
    private Long id;
    private String area;
    private String name;
    private DepartmentResponseDTO managedDepartment;
}
