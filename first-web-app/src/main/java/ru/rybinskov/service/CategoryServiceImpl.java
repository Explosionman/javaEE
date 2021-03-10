package ru.rybinskov.service;

import ru.rybinskov.dto.CategoryDto;
import ru.rybinskov.entities.Category;
import ru.rybinskov.repository.CategoryRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService{

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id);
        if(category == null) return null;
        return new CategoryDto(category);
    }

    @Override
    public Long countAll() {
        return categoryRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(CategoryDto categoryDto) {
        categoryRepository.saveOrUpdate(new Category(categoryDto));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
