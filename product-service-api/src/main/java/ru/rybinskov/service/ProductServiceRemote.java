package ru.rybinskov.service;

import ru.rybinskov.dto.ProductDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemote {
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    Long countAll();
}
