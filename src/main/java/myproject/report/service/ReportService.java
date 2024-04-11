package myproject.report.service;

import myproject.base.exception.NotFoundException;
import myproject.empoyee.dao.EmployeeDAO;
import myproject.empoyee.entity.Gender;
import myproject.report.dto.TopEmployeesByTotalHoursDTO;
import myproject.empoyee.entity.Employee;
import myproject.report.dao.ReportDAO;
import myproject.report.dto.AreasByAverageSalaryDTO;
import myproject.report.mapper.ReportMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Stateless
public class ReportService {
    @Inject
    ReportDAO reportDAO;
    @Inject
    EmployeeDAO employeeDAO;
    @Inject
    ReportMapper reportMapper;

    public List<AreasByAverageSalaryDTO> getTopAreaBySalaryInEachDepartmentDTO(Integer limit) {
        if (limit == null) limit = -1;
        return reportDAO.getTopAreaBySalaryInEachDepartment(limit);
    }

    public List<TopEmployeesByTotalHoursDTO> getTopEmployeesByTotalWorkingHoursOfDepartment(Long departmentId, Integer limit) throws NotFoundException {
        if (limit == null) limit = -1;
//        if (departmentDAO.findById(departmentId).isEmpty())
//            throw new NotFoundException("Department id cannot be found");
        List<Object[]> employees = employeeDAO.getTopEmployeesByTotalWorkingHoursOfDepartment(departmentId, limit);
        if (employees.isEmpty())
            throw new NotFoundException("Department id cannot be found");

        return employees.stream().map(objectArr -> {
            Employee employee = Employee.builder()
                    .lastName((String) objectArr[1])
                    .middleName((String) objectArr[2])
                    .firstName((String) objectArr[3])
                    .gender((Gender) objectArr[4])
                    .build();
            employee.setId((Long) objectArr[0]);
            Long totalWorkingHours = (Long) objectArr[5];
            TopEmployeesByTotalHoursDTO dto = reportMapper.toTopEmployeesByTotalHoursDTO(employee);
            dto.setTotalWorkingHours(Optional.ofNullable(totalWorkingHours).orElse(0L).intValue());
            return dto;
        }).sorted(Comparator.comparing(TopEmployeesByTotalHoursDTO::getTotalWorkingHours).reversed()).toList();
    }
}
