package ru.rybinskov.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.entities.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();

                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100), null));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200), null));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200), null));

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }
    }


    public List<Product> findAll() {
        return em.createNativeQuery("findAllProducts", Product.class).getResultList();
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
        em.createNativeQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }
}
