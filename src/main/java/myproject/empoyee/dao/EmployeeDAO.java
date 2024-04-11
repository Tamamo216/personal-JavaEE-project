package myproject.empoyee.dao;

import myproject.base.dao.BaseDAO;
import myproject.empoyee.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeDAO extends BaseDAO<Employee> {

    public EmployeeDAO() {
        super(Employee.class);
    }

    public List<Employee> getEmployeesOrderByFirstNameDesc() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        cq.orderBy(cb.desc(root.get("firstName")));
        TypedQuery<Employee> tq = em.createQuery(cq);
        return tq.getResultList();
    }

    public List<Employee> getEmployeesOrderByFirstNameAsc(int limit) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get("firstName")));
        TypedQuery<Employee> tq = em.createQuery(cq);
        if (limit != -1) tq.setMaxResults(limit);
        return tq.getResultList();
    }

    public Employee addEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public Optional<Employee> findEmployeeById(Long employeeId) {
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :employeeId", Employee.class);
        return query.setParameter("employeeId", employeeId).getResultStream().findFirst();
    }

    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        String queryString = "SELECT e FROM Employee e LEFT JOIN e.department d WHERE d.id = :departmentId ORDER BY e.firstName";
        TypedQuery<Employee> query = em.createQuery(
                queryString, Employee.class);
        return query.setParameter("departmentId", departmentId).getResultList();
    }

    public List<Object[]> getTopEmployeesByTotalWorkingHoursOfDepartment(Long departmentId, int limit) {
        EntityGraph<Employee> employeeEntityGraph = em.createEntityGraph(Employee.class);
        TypedQuery<Object[]> query = em.createNamedQuery("getEmployeesOrderByTotalHours", Object[].class);
        if (limit != -1)
            query.setMaxResults(limit);
        return query.setParameter("departmentId", departmentId)
                .setHint("javax.persistence.fetchgraph", employeeEntityGraph)
                .getResultList();
    }
}
