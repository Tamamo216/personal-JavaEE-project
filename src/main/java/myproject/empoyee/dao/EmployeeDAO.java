package myproject.empoyee.dao;

import myproject.base.dao.BaseDAO;
import myproject.empoyee.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
}
