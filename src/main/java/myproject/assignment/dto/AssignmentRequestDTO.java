package myproject.assignment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import myproject.empoyee.dto.EmployeeResponseDTO;
import myproject.project.dto.ProjectResponseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssignmentRequestDTO {
    private Integer numberOfHours;
    private Long employeeId;
    private Long projectId;
}
