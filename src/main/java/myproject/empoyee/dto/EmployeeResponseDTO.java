package myproject.empoyee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.department.dto.DepartmentResponseDTO;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeResponseDTO {
    private Long id;
    private LocalDate dateOfBirth;
    private String fullName;
    private String gender;
    private Long salary;
    private DepartmentResponseDTO department;
}
