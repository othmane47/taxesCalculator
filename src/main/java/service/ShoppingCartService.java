package service;


import domain.Item;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Shopping cart service.
 */
@Slf4j
public class ShoppingCartService {


    /**
     * Creat shopping cart shopping cart.
     *
     * @return the shopping cart
     */
    public ShoppingCart creatShoppingCart() {
        return ShoppingCart.builder()
                .totalPrices(BigDecimal.ZERO)
                .totalTaxes(BigDecimal.ZERO)
                .items(new ArrayList<>())
                .build();
    }

    /**
     * Creat shopping cart shopping cart.
     *
     * @param items the items
     * @return the shopping cart
     */
    public ShoppingCart creatShoppingCart(List<Item> items) {
        ShoppingCart shoppingCart = creatShoppingCart();
        shoppingCart.addItemsToCart(items);
        return shoppingCart;
    }

    /**
     * Add item to cart shopping cart.
     *
     * @param shoppingCart the shopping cart
     * @param item         the item
     * @return the shopping cart
     */
    public ShoppingCart addItemToCart(ShoppingCart shoppingCart, Item item) {
        shoppingCart.addItemToCart(item);
        return shoppingCart;
    }

}
