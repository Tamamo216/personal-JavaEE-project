package myproject.report.dao;

import myproject.report.dto.AreasByAverageSalaryDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ReportDAO {

    @PersistenceContext
    private EntityManager em;
    public List<AreasByAverageSalaryDTO> getTopAreaBySalaryInEachDepartment(int limit) {
        String queryString = "SELECT new myproject.report.dto.AreasByAverageSalaryDTO(p.area, AVG(e.salary)) " +
                "FROM Project p JOIN p.assignments a JOIN a.employee e " +
                "GROUP BY p.area ORDER BY AVG(e.salary) DESC";
        TypedQuery<AreasByAverageSalaryDTO> query = em.createQuery(queryString, AreasByAverageSalaryDTO.class);
        if(limit != -1)
            query.setMaxResults(limit);
        return query.getResultList();
    }
}
