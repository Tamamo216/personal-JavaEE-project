package myproject.department.mapper;

import myproject.department.dto.DepartmentDTO;
import myproject.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.CDI,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);
    Department toDepartment(DepartmentDTO departmentDTO);

    void updateDepartment(@MappingTarget Department department, DepartmentDTO departmentDTO);
}
