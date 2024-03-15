package myproject.project.mapper;

import myproject.project.dto.ProjectRequestDTO;
import myproject.project.dto.ProjectResponseDTO;
import myproject.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProjectMapper {
    ProjectResponseDTO toProjectDTO(Project project);
    List<ProjectResponseDTO> toProjectDTOs(List<Project> projects);
    Project toProject(ProjectRequestDTO projectRequestDTO);
    void updateProject(@MappingTarget Project project, ProjectRequestDTO projectRequestDTO);

}
