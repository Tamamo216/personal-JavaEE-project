package myproject.department.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentESResponseDTO {
    @JsonProperty("department_id")
    private Long id;
    private String name;
    private LocalDate startDate;
}
