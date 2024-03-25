package myproject.assignment.service;

import myproject.assignment.dao.AssignmentDAO;
import myproject.assignment.dto.AssignmentRequestDTO;
import myproject.assignment.dto.AssignmentResponseDTO;
import myproject.assignment.entity.Assignment;
import myproject.assignment.mapper.AssignmentMapper;
import myproject.base.exception.NotFoundException;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.entity.Employee;
import myproject.project.dao.ProjectDAO;
import myproject.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AssignmentService {
    @Inject
    AssignmentDAO assignmentDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    ProjectDAO projectDAO;
    @Inject
    AssignmentMapper assignmentMapper;
    public List<AssignmentResponseDTO> getAssignments(Integer limit) {
        if (limit == null) limit = -1;
        return assignmentMapper.toAssignmentDTOs(assignmentDAO.getAssignments(limit));
    }

    public AssignmentResponseDTO getAssignmentById(Long assignmentId) throws NotFoundException {
        Assignment assignment = assignmentDAO.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException("Assignment id cannot be found"));
        return assignmentMapper.toAssignmentDTO(assignment);
    }

    public AssignmentResponseDTO addAssignment(AssignmentRequestDTO request) throws NotFoundException {
        Assignment newAssignment = assignmentMapper.toAssignment(request);
        Employee employee = employeeDAO.findEmployeeById(request.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee id cannot be found"));
        Project project = projectDAO.findById(request.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project id cannot be found"));
        newAssignment.setEmployee(employee);
        newAssignment.setProject(project);

        return assignmentMapper.toAssignmentDTO(assignmentDAO.add(newAssignment));
    }
}
