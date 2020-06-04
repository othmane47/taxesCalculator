package service;

import domain.Category;
import domain.Product;

import java.math.BigDecimal;

public class ProductService {

    public Product createProduct(Category category, boolean isImported, BigDecimal htPrice, String name, int quantity) {
        Product product = Product.builder()
                .category(category)
                .htPrice(htPrice)
                .isImported(isImported)
                .name(name)
                .quantity(quantity)
                .build();
        product.calculateTaxes();
        product.calculateTtcPrice();
        return product;
    }
}
