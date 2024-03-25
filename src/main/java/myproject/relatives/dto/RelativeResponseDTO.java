package myproject.relatives.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.empoyee.dto.EmployeeResponseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RelativeResponseDTO {
    private Long id;
    private String fullName;
    private String gender;
    private String phoneNumber;
    private String relationship;
    private EmployeeResponseDTO employee;
}
