package service;

import domain.Item;
import domain.Product;
import domain.ShoppingCart;
import enums.CategoryEnum;
import enums.OriginEnum;
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
    private static ItemService itemService;
    private static Product product1, product2, product3;
    private static Item item1, item2;
    private static ShoppingCart shoppingCart;


    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        shoppingCartService = new ShoppingCartService();
        productService = new ProductService();
        itemService = new ItemService();

    }

    /**
     * Init.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @BeforeEach
    public void init() throws IllegalPriceException, IllegalQuantityException {
        product1 = productService.createProduct(CategoryEnum.FOOD, OriginEnum.IMPORTED, BigDecimal.valueOf(10), "boîtes de chocolats");
        product2 = productService.createProduct(CategoryEnum.GENERIC, OriginEnum.IMPORTED, BigDecimal.valueOf(47.50), "flacons de parfum");
        product3 = productService.createProduct(CategoryEnum.BOOK, OriginEnum.LOCAL, BigDecimal.valueOf(0.85), "livres");
        item1 = itemService.createItem(product1, 2);
        item2 = itemService.createItem(product2, 3);
        shoppingCart = ShoppingCart.builder()
                .itemList(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .totalPrices(BigDecimal.ZERO)
                .build();
    }

    /**
     * Should create shopping cart with given list of products.
     */
    @Test
    public void shouldCreateShoppingCart() {
        assertThat(shoppingCartService.creatShoppingCart()).isInstanceOf(ShoppingCart.class);
    }


    /**
     * Should create shopping cart with given list of products.
     */
    @Test
    public void shouldCreateShoppingCartWithList() {
        List<Item> items = Arrays.asList(item1, item2);
        assertThat(shoppingCartService.creatShoppingCart(items).getItemList().size()).isEqualTo(2);
    }

    @Test
    public void shouldAddItemToList() {
        shoppingCart.addItemToCart(item1);
        assertThat(shoppingCart.getItemList().size()).isEqualTo(1);
    }


    /**
     * Should get cart total tax.
     */
    @Test
    public void shouldGetCartTotalTax() {

        shoppingCartService.addItemToCart(shoppingCart, item1);
        shoppingCartService.addItemToCart(shoppingCart, item2);
        assertThat(shoppingCart.getTotalTaxes()).isEqualTo(BigDecimal.valueOf(36.65));
    }

    /**
     * Should get basket total price.
     */
    @Test
    public void shouldGetBasketTotalPrice() {

        shoppingCartService.addItemToCart(shoppingCart, item1);
        shoppingCartService.addItemToCart(shoppingCart, item2);

        assertThat(shoppingCart.getTotalPrices()).isEqualTo(BigDecimal.valueOf(199.15));

    }

    /**
     * Invoice printer.
     */
    @Test
    void invoicePrinter() {
        String expected = ("\n================ Invoice ================\n" +
                "* 2 boîtes de chocolats importé à 10.00€ : 21.00€ TTC\n" +
                "* 3 flacons de parfum importé à 47.50€ : 178.15€ TTC\n" +
                "\nMontant des taxes : 36.65€" +
                "\nTotal : 199.15€");
        shoppingCartService.addItemToCart(shoppingCart, item1);
        shoppingCartService.addItemToCart(shoppingCart, item2);
        assertThat(shoppingCart.invoicePrinter()).isEqualToIgnoringCase(expected);
    }

}
