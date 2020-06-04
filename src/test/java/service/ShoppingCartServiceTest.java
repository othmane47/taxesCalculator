package service;

import domain.Category;
import domain.Product;
import domain.ShoppingCart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShoppingCartServiceTest {

    private static ShoppingCartService shoppingCartService;
    private static ProductService productService;
    private static Product product1, product2, product3;
    private static ShoppingCart shoppingCart;


    @BeforeAll
    public static void setUp() {
        shoppingCartService = new ShoppingCartService();
        productService = new ProductService();

    }

    @BeforeEach
    public void init() {
        product1 = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(12.49), "roman", 2);
        product2 = productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD", 1);
        product3 = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        shoppingCart = ShoppingCart.builder()
                .products(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .totalPrices(BigDecimal.ZERO)
                .build();
    }

    @Test
    public void shouldCreateShoppingCart(){
        List<Product> products= Arrays.asList(product1,product2,product3);
        assertThat(shoppingCartService.creatShoppingCart(products).getProducts().size()).isEqualTo(3);
    }

    @Test
    public void shouldGetCartTotalTax() {

        shoppingCartService.addProductToCart(shoppingCart, product1);
        shoppingCartService.addProductToCart(shoppingCart, product2);
        shoppingCartService.addProductToCart(shoppingCart, product3);

        assertThat(shoppingCart.getTotalTaxes()).isEqualTo(BigDecimal.valueOf(5.53).setScale(2));
    }

    @Test
    public void shouldGetBasketTotalPrice() {

        shoppingCartService.addProductToCart(shoppingCart, product1);
        shoppingCartService.addProductToCart(shoppingCart, product2);
        shoppingCartService.addProductToCart(shoppingCart, product3);

        assertThat(shoppingCart.getTotalPrices()).isEqualTo(BigDecimal.valueOf(48.05).setScale(2));

    }

}
