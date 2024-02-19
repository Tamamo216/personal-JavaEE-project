package com.axonactive.empoyee.dao;

import com.axonactive.base.dao.BaseDAO;
import com.axonactive.empoyee.entity.Employee;

import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

@Stateless
public class EmployeeDAO extends BaseDAO<Employee> {

    public EmployeeDAO() {
        super(Employee.class);
    }

    public List<Employee> getEmployeesOrderByFirstName(boolean isDesc) {
        List<Employee> employees = findAll();
        if (!isDesc)
            return employees.stream().sorted(Comparator.comparing(Employee::getFirstName).reversed()).toList();
        return employees.stream().sorted(comparing(Employee::getFirstName)).toList();
    }
}
