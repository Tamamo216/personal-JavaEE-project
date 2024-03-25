package myproject.assignment.mapper;

import myproject.assignment.dto.AssignmentRequestDTO;
import myproject.assignment.dto.AssignmentResponseDTO;
import myproject.assignment.entity.Assignment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AssignmentMapper {

    AssignmentResponseDTO toAssignmentDTO(Assignment assignment);
    List<AssignmentResponseDTO> toAssignmentDTOs(List<Assignment> assignments);
    Assignment toAssignment(AssignmentRequestDTO assignmentDTO);
}
