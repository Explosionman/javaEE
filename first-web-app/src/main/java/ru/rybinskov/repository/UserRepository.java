package ru.rybinskov.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserRepository implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    public UserRepository() {
    }

    @TransactionAttribute
    public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        }
        return em.merge(user);
    }

    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting user");

        try {
            User attached = findById(id);
            if (attached != null) {
                em.remove(attached);
            }
        } catch (Exception ex) {
            logger.error("Error with entity class", ex);
            throw new IllegalStateException(ex);
        }
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    @TransactionAttribute
    public List<User> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);
        from.fetch("roles", JoinType.LEFT);
        query.select(from).distinct(true);

        return em.createQuery(query).getResultList();

//        return em.createQuery("select distinct u from User u left join fetch u.roles", User.class)
//                .getResultList();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllUsers", Long.class)
                .getSingleResult();
    }
}
