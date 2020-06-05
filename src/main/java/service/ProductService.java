package service;

import domain.Category;
import domain.Product;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;

import java.math.BigDecimal;

/**
 * The type Product service.
 */
public class ProductService {

    /**
     * Create product product.
     *
     * @param category   the category
     * @param isImported the is imported
     * @param htPrice    the ht price
     * @param name       the name
     * @param quantity   the quantity
     * @return the product
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    public Product createProduct(Category category, boolean isImported, BigDecimal htPrice, String name, int quantity) throws IllegalPriceException, IllegalQuantityException {
        if (htPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalPriceException("Price should be higher than 0€");
        if (quantity < 1)
            throw new IllegalQuantityException("Quantity should be higher than 1");
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
