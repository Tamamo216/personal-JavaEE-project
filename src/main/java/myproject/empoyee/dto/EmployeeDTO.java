package myproject.empoyee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import myproject.department.dto.DepartmentDTO;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    @JsonFormat(locale = "vi-VN")
    private LocalDate dateOfBirth;
    private String fullName;
    private String gender;
    private int salary;
    private DepartmentDTO department;
}
