package myproject.empoyee.dto;

import myproject.department.dto.DepartmentDTO;
import myproject.empoyee.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    @JsonFormat(locale = "vi-VN")
    private LocalDate dateOfBirth;
    private String fullName;
    private Gender gender;
    private int salary;
    private DepartmentDTO department;
}
