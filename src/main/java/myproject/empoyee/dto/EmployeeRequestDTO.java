package myproject.empoyee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeRequestDTO {
    private Long id;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Long salary;
    private Long departmentId;
}
