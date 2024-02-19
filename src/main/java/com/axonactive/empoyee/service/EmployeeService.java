package com.axonactive.empoyee.service;

import com.axonactive.empoyee.dao.EmployeeDAO;
import com.axonactive.empoyee.dto.EmployeeDTO;
import com.axonactive.empoyee.entity.Employee;
import com.axonactive.empoyee.mapper.EmployeeMapper;

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
        List<Employee> employees = employeeDAO.getEmployeesOrderByFirstName(isDesc);
        return employees.stream().map(mapper::toEmployeeDTO).toList();
    }
}
