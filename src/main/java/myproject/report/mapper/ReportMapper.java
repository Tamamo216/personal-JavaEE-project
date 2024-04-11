package myproject.report.mapper;

import myproject.empoyee.entity.Employee;
import myproject.report.dto.TopEmployeesByTotalHoursDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "cdi")
public interface ReportMapper {
    @Mapping(target = "fullName", source = ".", qualifiedByName = "getFullName")
    TopEmployeesByTotalHoursDTO toTopEmployeesByTotalHoursDTO(Employee employee);

    @Named("getFullName")
    default String getFullName(Employee employee) {
        return String.format("%s %s %s", employee.getLastName(), employee.getMiddleName(), employee.getFirstName());
    }

}
