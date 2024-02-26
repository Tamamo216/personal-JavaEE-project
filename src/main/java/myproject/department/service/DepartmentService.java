package myproject.department.service;

import myproject.department.mapper.DepartmentMapper;
import myproject.department.dao.DepartmentDAO;
import myproject.department.dto.DepartmentDTO;
import myproject.department.entity.Department;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;
    @Inject
    DepartmentMapper mapper;
    public List<DepartmentDTO> getDepartments(boolean isDesc) {
        List<Department> departments = departmentDAO.getSortedDepartments(isDesc);
        return departments.stream().map(mapper::toDepartmentDTO).toList();
    }
}
