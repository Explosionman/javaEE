package ru.rybinskov.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.entities.Role;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

    private final Logger logger = LoggerFactory.getLogger(RoleRepository.class);

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @TransactionAttribute
    public Role merge(Role role) {
        return em.merge(role);
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> findAll() {
        return em.createQuery("from Role ", Role.class).getResultList();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllRoles", Long.class)
                .getSingleResult();
    }
}
