package myproject.empoyee.mapper;

import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.dto.EmployeeResponseDTO;
import myproject.empoyee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
    @Mapping(target = "fullName", source = ".", qualifiedByName = "getFullName")
    EmployeeResponseDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);
    List<EmployeeResponseDTO> toEmployeeDTOs(List<Employee> employees);

    @Named("getFullName")
    default String getFullName(Employee employee) {
        return String.format("%s %s %s", employee.getLastName(), employee.getMiddleName(), employee.getFirstName());
    }
}
