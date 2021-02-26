package ru.rybinskov.controller;

import ru.rybinskov.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Long, Product> productMap = new HashMap<>();


    public void addToCart(Product product) {
        productMap.put(product.getId(), product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public void removeFromCart(Product product) {
        productMap.remove(product.getId());
    }

    public int getCartSize() {
        return productMap.size();
    }
}
