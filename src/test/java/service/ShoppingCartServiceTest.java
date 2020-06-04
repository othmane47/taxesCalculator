package service;

import domain.Category;
import domain.Product;
import domain.ShoppingCart;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Shopping cart service test.
 */
class ShoppingCartServiceTest {

    private static ShoppingCartService shoppingCartService;
    private static ProductService productService;
    private static Product product1, product2, product3;
    private static ShoppingCart shoppingCart;


    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        shoppingCartService = new ShoppingCartService();
        productService = new ProductService();

    }

    /**
     * Init.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @BeforeEach
    public void init() throws IllegalPriceException, IllegalQuantityException {
        product1 = productService.createProduct(Category.BOOK, true, BigDecimal.valueOf(10), "roman", 2);
        product2 = productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(47.50), "CD", 3);
        product3 = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        shoppingCart = ShoppingCart.builder()
                .products(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .totalPrices(BigDecimal.ZERO)
                .build();
    }

    /**
     * Should create shopping cart.
     */
    @Test
    public void shouldCreateShoppingCart(){
        List<Product> products= Arrays.asList(product1,product2,product3);
        assertThat(shoppingCartService.creatShoppingCart(products).getProducts().size()).isEqualTo(3);
    }

    /**
     * Should get cart total tax.
     */
    @Test
    public void shouldGetCartTotalTax() {

        shoppingCartService.addProductToCart(shoppingCart, product1);
        shoppingCartService.addProductToCart(shoppingCart, product2);
        //shoppingCartService.addProductToCart(shoppingCart, product3);

        assertThat(shoppingCart.getTotalTaxes()).isEqualTo(BigDecimal.valueOf(36.65).setScale(2));
    }

    /**
     * Should get basket total price.
     */
    @Test
    public void shouldGetBasketTotalPrice() {

        shoppingCartService.addProductToCart(shoppingCart, product1);
        shoppingCartService.addProductToCart(shoppingCart, product2);
       // shoppingCartService.addProductToCart(shoppingCart, product3);

        assertThat(shoppingCart.getTotalPrices()).isEqualTo(BigDecimal.valueOf(199.15).setScale(2));

    }

}
