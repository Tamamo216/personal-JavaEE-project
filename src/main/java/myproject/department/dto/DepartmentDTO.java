package myproject.department.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DepartmentDTO {
    private String name;
    @JsonFormat(locale = "vi-VN")
    private LocalDate startDate;
}
