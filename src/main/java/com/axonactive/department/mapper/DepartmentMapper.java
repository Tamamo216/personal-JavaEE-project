package com.axonactive.department.mapper;

import com.axonactive.department.dto.DepartmentDTO;
import com.axonactive.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);
    Department toDepartment(DepartmentDTO departmentDTO);
}
