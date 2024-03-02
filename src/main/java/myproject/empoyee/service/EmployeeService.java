package myproject.empoyee.service;

import myproject.base.exception.NotFoundException;
import myproject.department.dao.DepartmentDAO;
import myproject.department.entity.Department;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.dto.EmployeeResponseDTO;
import myproject.empoyee.entity.Employee;
import myproject.empoyee.mapper.EmployeeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.List;

@Stateless
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private DepartmentDAO departmentDAO;
    @Inject
    private EmployeeMapper mapper;
    public List<EmployeeResponseDTO> getAllEmployees(boolean isDesc) {
        if (!isDesc)
            return mapper.toEmployeeDTOs(employeeDAO.getEmployeesOrderByFirstNameAsc());
        return  mapper.toEmployeeDTOs(employeeDAO.getEmployeesOrderByFirstNameDesc());
    }

    public EmployeeResponseDTO findEmployeeById(Long employeeId) throws NotFoundException {
        Employee employee =  employeeDAO.findEmployeeById(employeeId).orElseThrow(
                () -> new NotFoundException("Employee id doesn't exist"));
        return mapper.toEmployeeDTO(employee);
    }

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequest) throws NotFoundException, ValidationException {
        validateAddEmployeeRequest(employeeRequest);
        Employee newEmployee = mapper.toEmployee(employeeRequest);
        Department department = departmentDAO.findById(employeeRequest.getDepartmentId()).orElseThrow(
                () -> new NotFoundException("Department id doesn't exist"));
        newEmployee.setDepartment(department);
        return mapper.toEmployeeDTO(employeeDAO.addEmployee(newEmployee));
    }

    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequest, Long employeeId) throws NotFoundException, ValidationException {
        Employee employee = employeeDAO.findEmployeeById(employeeId).orElseThrow(
                () -> new NotFoundException("Employee id doesn't exist"));
        mapper.updatePartialEmployee(employee, employeeRequest);
        return mapper.toEmployeeDTO(employeeDAO.update(employee));
    }

    private void validateAddEmployeeRequest(EmployeeRequestDTO request) throws ValidationException {
        if (request.getFirstName() == null || request.getFirstName().isBlank())
            throw new ValidationException("First name cannot be blank");
        else if (request.getMiddleName() == null || request.getMiddleName().isBlank())
            throw new ValidationException("Middle name cannot be blank");
        else if (request.getLastName() == null || request.getLastName().isBlank())
            throw new ValidationException("Last name cannot be blank");
    }
}
