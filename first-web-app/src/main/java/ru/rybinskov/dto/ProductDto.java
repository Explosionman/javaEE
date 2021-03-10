package ru.rybinskov.dto;

import ru.rybinskov.entities.Category;
import ru.rybinskov.entities.Product;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long categoryId;

    private String categoryName;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        Category category = product.getCategory();
        this.categoryId = category != null ? category.getId() : null;
        this.categoryName = category != null ? category.getName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
