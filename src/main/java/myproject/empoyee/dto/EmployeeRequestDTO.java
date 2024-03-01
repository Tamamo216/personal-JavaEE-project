package myproject.empoyee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeRequestDTO {
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Middle name cannot be blank")
    private String middleName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    private String gender;
    private Long salary;
    private Long departmentId;
}
