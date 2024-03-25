package myproject.relatives.dao;

import myproject.base.dao.BaseDAO;
import myproject.relatives.entity.Relative;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class RelativeDAO extends BaseDAO<Relative> {

    public RelativeDAO() {
        super(Relative.class);
    }

    public List<Relative> getRelatives(int limit) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relative> cq = cb.createQuery(Relative.class);
        Root<Relative> root = cq.from(Relative.class);
        cq.select(root);
        TypedQuery<Relative> query = em.createQuery(cq);
        if (limit != -1)
            query.setMaxResults(limit);
        return query.getResultList();
    }

    public void removeRelative(Long relativeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Relative> cq = cb.createCriteriaDelete(Relative.class);
        Root<Relative> root = cq.from(Relative.class);
        cq.where(cb.equal(root.get("id"), relativeId));
        em.createQuery(cq).executeUpdate();
    }
}
