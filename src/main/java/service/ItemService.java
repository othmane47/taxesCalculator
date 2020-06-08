package service;

import domain.Item;
import domain.Product;
import exception.IllegalQuantityException;

/**
 * The type Item service.
 */
public class ItemService {


    /**
     * Create item item.
     *
     * @param product  the product
     * @param quantity the quantity
     * @return the item
     * @throws IllegalQuantityException the illegal quantity exception
     */
    public Item createItem(Product product, int quantity) throws IllegalQuantityException {
        if (quantity < 1)
            throw new IllegalQuantityException();

        return Item.builder()
                .product(product)
                .quantity(quantity)
                .build();

    }
}
