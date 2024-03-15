package myproject.empoyee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.department.dto.DepartmentResponseDTO;
import myproject.project.dto.ProjectResponseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeProjectsDTO {
    private Long id;
    private String fullName;
    private String gender;
    private DepartmentResponseDTO department;
    private List<ProjectResponseDTO> projects;
}
