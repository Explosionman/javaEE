package ru.rybinskov;

import ru.rybinskov.service.ProductService;
import ru.rybinskov.service.ProductServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class WsMain {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/first-web-app/ProductService/ProductServiceImpl?wsdl");
        ProductService productService = new ProductService(url);

        ProductServiceWs port = productService.getProductServiceImplPort();

        port.findAll().forEach(product -> System.out.println(product.getName()));
    }
}
