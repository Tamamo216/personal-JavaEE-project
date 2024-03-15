package myproject.department.mapper;

import myproject.department.dto.DepartmentRequestDTO;
import myproject.department.dto.DepartmentResponseDTO;
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
    DepartmentResponseDTO toDepartmentDTO(Department department);
    Department toDepartment(DepartmentRequestDTO departmentDTO);

    void updateDepartment(@MappingTarget Department department, DepartmentRequestDTO departmentDTO);
}
