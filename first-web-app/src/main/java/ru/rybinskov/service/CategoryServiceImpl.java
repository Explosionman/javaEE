package ru.rybinskov.service;

import ru.rybinskov.dto.CategoryDto;
import ru.rybinskov.entities.Category;
import ru.rybinskov.repository.CategoryRepository;
import ru.rybinskov.rest.CategoryRestService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryRestService {

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

    @Override
    public void update(CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            saveOrUpdate(categoryDto);
        }
    }

    @Override
    public void save(CategoryDto categoryDto) {
        if(categoryDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(categoryDto);
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
