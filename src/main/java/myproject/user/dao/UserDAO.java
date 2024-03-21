package myproject.user.dao;

import myproject.base.dao.BaseDAO;
import myproject.user.entity.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateless
public class UserDAO extends BaseDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = em.createNamedQuery("User_FindByEmail", User.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }


}
