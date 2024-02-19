package com.axonactive.empoyee.mapper;

import com.axonactive.empoyee.dto.EmployeeDTO;
import com.axonactive.empoyee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
    EmployeeDTO toEmployeeDTO(Employee employee);

    Employee toEmployee(EmployeeDTO employeeDTO);
}
