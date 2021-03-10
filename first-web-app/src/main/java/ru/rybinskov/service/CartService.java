package ru.rybinskov.service;

import ru.rybinskov.dto.ProductDto;

import java.util.List;

public interface CartService {

    void addToCart(ProductDto product);

    void removeFromCart(ProductDto product);

    int getSize();

    List<ProductDto> getProductsInCart();
}
