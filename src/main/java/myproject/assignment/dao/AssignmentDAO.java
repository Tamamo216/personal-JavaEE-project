package myproject.assignment.dao;

import myproject.assignment.entity.Assignment;
import myproject.base.dao.BaseDAO;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AssignmentDAO extends BaseDAO<Assignment> {

    public AssignmentDAO() {
        super(Assignment.class);
    }

    public List<Assignment> getAssignments(int limit) {
        TypedQuery<Assignment> query = em.createQuery("SELECT a FROM Assignment a", Assignment.class);
        if (limit != -1)
            query.setMaxResults(limit);
        return query.getResultList();
    }

}
