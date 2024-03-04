package myproject.department.service;

import myproject.department.dao.DepartmentDAO;
import myproject.department.dto.DepartmentDTO;
import myproject.department.entity.Department;
import myproject.department.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;
    @Inject
    DepartmentMapper departmentMapper;
    public List<DepartmentDTO> getDepartments(boolean isDesc) {
        List<Department> departments = departmentDAO.getSortedDepartments(isDesc);
        return departments.stream().map(departmentMapper::toDepartmentDTO).toList();
    }
}
