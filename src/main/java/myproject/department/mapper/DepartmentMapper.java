package myproject.department.mapper;

import myproject.department.dto.DepartmentDTO;
import myproject.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);
    Department toDepartment(DepartmentDTO departmentDTO);
}
