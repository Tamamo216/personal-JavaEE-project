package myproject.empoyee.service;

import myproject.base.exception.NotFoundException;
import myproject.department.dao.DepartmentDAO;
import myproject.department.entity.Department;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.dto.EmployeeProjectsDTO;
import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.dto.EmployeeResponseDTO;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.mapper.EmployeeMapper;
import myproject.project.dto.ProjectResponseDTO;
import myproject.project.entity.Project;
import myproject.project.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Stateless
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private DepartmentDAO departmentDAO;
    @Inject
    private EmployeeMapper employeeMapper;
    @Inject
    private ProjectMapper projectMapper;
    public List<EmployeeResponseDTO> getAllEmployees(boolean isDesc) {
        if (!isDesc)
            return employeeMapper.toEmployeeDTOs(employeeDAO.getEmployeesOrderByFirstNameAsc());
        return  employeeMapper.toEmployeeDTOs(employeeDAO.getEmployeesOrderByFirstNameDesc());
    }

    public EmployeeResponseDTO findEmployeeById(Long employeeId) throws NotFoundException {
        Employee employee =  employeeDAO.findEmployeeById(employeeId).orElseThrow(
                () -> new NotFoundException("Employee id doesn't exist"));
        return employeeMapper.toEmployeeDTO(employee);
    }

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequest) throws NotFoundException, ValidationException {
        validateAddEmployeeRequest(employeeRequest);
        Employee newEmployee = employeeMapper.toEmployee(employeeRequest);
        Department department = departmentDAO.findById(employeeRequest.getDepartmentId()).orElseThrow(
                () -> new NotFoundException("Department id doesn't exist"));
        newEmployee.setDepartment(department);
        return employeeMapper.toEmployeeDTO(employeeDAO.addEmployee(newEmployee));
    }

    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequest, Long employeeId) throws NotFoundException, ValidationException {
        Employee employee = employeeDAO.findEmployeeById(employeeId).orElseThrow(
                () -> new NotFoundException("Employee id doesn't exist"));
        employeeMapper.updatePartialEmployee(employee, employeeRequest);
        return employeeMapper.toEmployeeDTO(employeeDAO.update(employee));
    }

    private void validateAddEmployeeRequest(EmployeeRequestDTO request) throws ValidationException {
        if (request.getFirstName() == null || request.getFirstName().isBlank())
            throw new ValidationException("First name cannot be blank");
        else if (request.getMiddleName() == null || request.getMiddleName().isBlank())
            throw new ValidationException("Middle name cannot be blank");
        else if (request.getLastName() == null || request.getLastName().isBlank())
            throw new ValidationException("Last name cannot be blank");
    }

    public List<EmployeeResponseDTO> getEmployeesByDepartment(Long departmentId) {
        List<Employee> employees = employeeDAO.getEmployeesByDepartment(departmentId);
        return employeeMapper.toEmployeeDTOs(employees);
    }

    public List<EmployeeProjectsDTO> getEmployeesWithProjects(String area) {
        Map<Long, List<ProjectResponseDTO>> projectsByEmployeeId = new HashMap<>();
        Set<Employee> employees = new LinkedHashSet<>();

        employeeDAO.getEmployeesWithProjects(area).forEach(emplAndProj -> {
            Employee employee = (Employee) emplAndProj[0];
            Project project = (Project) emplAndProj[1];
            if (projectsByEmployeeId.containsKey(employee.getId())) {
                projectsByEmployeeId.get(employee.getId()).add(projectMapper.toProjectDTO(project));
            }
            else {
                projectsByEmployeeId.put(
                        employee.getId(), new ArrayList<>(Arrays.asList(projectMapper.toProjectDTO(project))));
            }
            employees.add(employee);
        });

        return employees.stream().map(employee -> {
            EmployeeProjectsDTO employeeProjectsDTO = employeeMapper.toEmployeeProjectsDTO(employee);
            employeeProjectsDTO.setProjects(projectsByEmployeeId.get(employee.getId()));
            return employeeProjectsDTO;
        }).toList();
    }
}
