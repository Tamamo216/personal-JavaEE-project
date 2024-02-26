package myproject.empoyee.mapper;

import myproject.empoyee.dto.EmployeeDTO;
import myproject.empoyee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> toEmployeesDTO(List<Employee> employees);
}
