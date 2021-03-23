package ru.rybinskov.service;

import ru.rybinskov.dto.ProductDto;
import java.util.List;

public interface ProductServiceRemote {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();
}
