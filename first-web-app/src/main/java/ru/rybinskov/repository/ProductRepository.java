package ru.rybinskov.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProducts", Product.class).getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProducts", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

    public Product findByName(String name) {
        return em.createNamedQuery("findProductByName", Product.class).setParameter("name", name).getSingleResult();
    }

    public List<Product> findAllByCategoryId(Long categoryId) {
        return em.createNamedQuery("findProductsByCategoryId", Product.class).setParameter("category_id", categoryId).getResultList();
    }
}
