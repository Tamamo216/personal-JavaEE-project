package myproject.empoyee.service;

import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.dto.EmployeeDTO;
import myproject.empoyee.mapper.EmployeeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private EmployeeMapper mapper;
    public List<EmployeeDTO> getEmployees(boolean isDesc) {
        return  mapper.toEmployeesDTO(employeeDAO.getEmployeesOrderByFirstName(isDesc));

    }
}
