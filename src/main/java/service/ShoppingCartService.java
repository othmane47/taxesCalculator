package service;


import domain.Product;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
public class ShoppingCartService {

    public ShoppingCart creatShoppingCart(List<Product> products){
        ShoppingCart shoppingCart=ShoppingCart.builder()
                .totalPrices(BigDecimal.ZERO)
                .totalTaxes(BigDecimal.ZERO)
                .build();
        shoppingCart.addItemsToCart(products);
        return shoppingCart;
    }

    public ShoppingCart addProductToCart(ShoppingCart shoppingCart, Product product) {
        shoppingCart.addItemToCart(product);
        return shoppingCart;
    }

}
