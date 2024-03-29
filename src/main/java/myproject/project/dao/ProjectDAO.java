package myproject.project.dao;

import myproject.base.dao.BaseDAO;
import myproject.department.entity.Department;
import myproject.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ProjectDAO extends BaseDAO<Project> {

    public ProjectDAO() {
        super(Project.class);
    }

    public List<Project> getProjects(int limit, String orderBy) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.select(root);
        cq.orderBy(cb.desc(root.get(orderBy)));
        TypedQuery<Project> query = em.createQuery(cq);
        if (limit != -1) query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<Project> getProjectsByDepartment(Long departmentId, String orderBy) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.select(root);
        Join<Project, Department> departmentJoin = root.join("managedDepartment", JoinType.LEFT);
        cq.where(cb.equal(departmentJoin.get("id"), departmentId));
        cq.orderBy(cb.desc(root.get(orderBy)));
        TypedQuery<Project> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Project> getProjectsByEmployee(Long employeeId, int limit) {
        EntityGraph<?> projectEntityGraph = em.getEntityGraph("entityGraphForProjectsByEmployee");
//        EntityGraph<Project> projectEntityGraph = em.createEntityGraph(Project.class);
//        projectEntityGraph.addAttributeNodes("managedDepartment");
        TypedQuery<Project> query = em.createNamedQuery("getProjectsByEmployeeId", Project.class);

        query.setParameter("employeeId", employeeId);
        if (limit != -1)
            query.setMaxResults(limit);
        return query.setHint("javax.persistence.fetchgraph", projectEntityGraph).getResultList();
    }
}
