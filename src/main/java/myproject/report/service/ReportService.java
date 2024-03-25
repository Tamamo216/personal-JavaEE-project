package myproject.report.service;

import myproject.report.dao.ReportDAO;
import myproject.report.dto.AreasByAverageSalaryDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ReportService {
    @Inject
    ReportDAO reportDAO;

    public List<AreasByAverageSalaryDTO> getTopAreaBySalaryInEachDepartmentDTO(Integer limit) {
        if (limit == null) limit = -1;
        return reportDAO.getTopAreaBySalaryInEachDepartment(limit);
    }
}
