package myproject.base.dao;

import myproject.base.entity.BaseEntity;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseDAO<T extends BaseEntity> {
    @PersistenceContext
    protected EntityManager em;
    private final Class<T> entityClass;

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return em.createQuery(query).getResultList();
    }

    public T add(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        em.merge(entity);
        return entity;
    }

    public Optional<T> findById(long id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public void delete(long id) {
        T entity = this.findById(id).get();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
