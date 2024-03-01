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

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequest) throws NotFoundException {
        Employee newEmployee = mapper.toEmployee(employeeRequest);
        Department department = departmentDAO.findById(employeeRequest.getDepartmentId()).orElseThrow(
                () -> new NotFoundException("Department id doesn't exist"));
        newEmployee.setDepartment(department);
        return mapper.toEmployeeDTO(employeeDAO.addEmployee(newEmployee));
    }
}
