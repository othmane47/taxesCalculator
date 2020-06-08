package service;

import domain.Product;
import enums.CategoryEnum;
import enums.OriginEnum;
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
     * @param category the categoryEnum
     * @param origin   the origin
     * @param htPrice  the ht price
     * @param name     the name
     * @return the product
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    public Product createProduct(CategoryEnum category, OriginEnum origin, BigDecimal htPrice, String name) throws IllegalPriceException, IllegalQuantityException {
        if (htPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalPriceException();

        return Product.builder()
                .categoryEnum(category)
                .htPrice(htPrice.setScale(2))
                .originEnum(origin)
                .name(name)
                .build();

    }
}
