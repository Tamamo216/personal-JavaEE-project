package myproject.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProjectRequestDTO {
    private Long id;
    private String area;
    private String name;
    private Long departmentId;
}
