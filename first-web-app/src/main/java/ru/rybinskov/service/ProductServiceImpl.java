package ru.rybinskov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.dto.ProductDto;
import ru.rybinskov.entities.Category;
import ru.rybinskov.entities.Product;
import ru.rybinskov.repository.CategoryRepository;
import ru.rybinskov.repository.ProductRepository;
import ru.rybinskov.rest.ProductRestService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService(endpointInterface = "ru.rybinskov.service.ProductServiceWs", serviceName = "ProductService")
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductRestService, ProductServiceWs {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto buildProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        Category category = product.getCategory();
        productDto.setCategoryId(category != null ? category.getId() : null);
        productDto.setCategoryName(category != null ? category.getName() : null);
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return buildProductDto(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void save(ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @Override
    public void update(ProductDto product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductDto product) {
        logger.info("Saving product with id {}", product.getId());

        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findByName(String name) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            return null;
        }
        return buildProductDto(product);
    }

    @Override
    public List<ProductDto> findAllByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream()
                .map(this::buildProductDto).collect(Collectors.toList());
    }
}
