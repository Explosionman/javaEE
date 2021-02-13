package ru.rybinskov.listener;


import ru.rybinskov.persist.Category;
import ru.rybinskov.persist.Product;
import ru.rybinskov.persist.Role;
import ru.rybinskov.persist.User;
import ru.rybinskov.repository.CategoryRepository;
import ru.rybinskov.repository.ProductRepository;
import ru.rybinskov.repository.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        UserRepository userRepository = new UserRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        categoryRepository.saveOrUpdate(new Category(null, "Напитки", "всё что пьётся"));
        categoryRepository.saveOrUpdate(new Category(null, "Фрукты", "арбуз тоже ягода"));

        userRepository.saveOrUpdate(new User(null, "Alex", "123", Role.ROLE_ADMIN));
        userRepository.saveOrUpdate(new User(null, "user", "user", Role.ROLE_USER));

        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
