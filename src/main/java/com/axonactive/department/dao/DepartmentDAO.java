package com.axonactive.department.dao;

import com.axonactive.base.dao.BaseDAO;
import com.axonactive.department.entity.Department;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {

    public DepartmentDAO() {
        super(Department.class);
    }

    public List<Department> getSortedDepartments(boolean isDesc) {
        TypedQuery<Department> query;
        if (!isDesc)
            query = em.createQuery("SELECT d FROM Department d ORDER BY d.name ASC", Department.class);
        else
            query = em.createQuery("SELECT d FROM Department d ORDER BY d.name DESC", Department.class);
        return query.getResultList();
    }
}
