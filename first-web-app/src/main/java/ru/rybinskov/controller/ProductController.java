package ru.rybinskov.controller;

import ru.rybinskov.dto.CategoryDto;
import ru.rybinskov.entities.Category;
import ru.rybinskov.repository.CategoryRepository;
import ru.rybinskov.dto.ProductDto;
import ru.rybinskov.service.CategoryService;
import ru.rybinskov.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private HttpSession httpSession;

    private ProductDto product;

    private List<ProductDto> products;

    private List<CategoryDto> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productService.findAll();
        categories = categoryService.findAll();
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto product) {
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public String logout() {
        httpSession.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
