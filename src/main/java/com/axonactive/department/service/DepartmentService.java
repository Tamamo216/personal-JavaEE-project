package com.axonactive.department.service;

import com.axonactive.department.mapper.DepartmentMapper;
import com.axonactive.department.dao.DepartmentDAO;
import com.axonactive.department.dto.DepartmentDTO;
import com.axonactive.department.entity.Department;

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
