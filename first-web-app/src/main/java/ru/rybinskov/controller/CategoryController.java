package ru.rybinskov.controller;

import ru.rybinskov.dto.CategoryDto;
import ru.rybinskov.service.CategoryService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private CategoryService categoryService;

    private CategoryDto category;

    private List<CategoryDto> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryService.findAll();
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new CategoryDto();
        return "category_form.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll();
    }

    public String editCategory(CategoryDto category) {
        this.category = category;
        return "category_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(CategoryDto category) {
        categoryService.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryService.saveOrUpdate(category);
        return "category.xhtml?faces-redirect=true";
    }
}
