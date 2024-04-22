package myproject.empoyee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeESResponseDTO {

    @JsonProperty(value = "employee_id")
    private Long id;

    private LocalDate dateOfBirth;
    private String fullName;
    private String gender;
    private Long salary;
}
