package myproject.empoyee.dao;

import myproject.base.dao.BaseDAO;
import myproject.empoyee.entity.Employee;

import javax.ejb.Stateless;
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

    public List<Employee> getEmployeesOrderByFirstNameAsc() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get("firstName")));
        TypedQuery<Employee> tq = em.createQuery(cq);
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
}
