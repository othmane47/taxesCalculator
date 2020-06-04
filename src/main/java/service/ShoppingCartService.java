package service;


import domain.Product;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;


/**
 * The type Shopping cart service.
 */
@Slf4j
public class ShoppingCartService {

    /**
     * Creat shopping cart shopping cart.
     *
     * @param products the products
     * @return the shopping cart
     */
    public ShoppingCart creatShoppingCart(List<Product> products){
        ShoppingCart shoppingCart=ShoppingCart.builder()
                .totalPrices(BigDecimal.ZERO)
                .totalTaxes(BigDecimal.ZERO)
                .build();
        shoppingCart.addProductsToCart(products);
        return shoppingCart;
    }

    /**
     * Add product to cart shopping cart.
     *
     * @param shoppingCart the shopping cart
     * @param product      the product
     * @return the shopping cart
     */
    public ShoppingCart addProductToCart(ShoppingCart shoppingCart, Product product) {
        shoppingCart.addProductToCart(product);
        return shoppingCart;
    }

}
