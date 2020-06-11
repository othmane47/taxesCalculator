package service;

import domain.Item;
import domain.Product;
import enums.CategoryEnum;
import enums.OriginEnum;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * The type Item service test.
 */
class ItemServiceTest {
    private static ItemService itemService;
    private static ProductService productService;
    private static Product product;

    /**
     * Sets up.
     *
     * @throws IllegalQuantityException the illegal quantity exception
     * @throws IllegalPriceException    the illegal price exception
     */
    @BeforeAll
    public static void setUp() throws IllegalQuantityException, IllegalPriceException {
        itemService = new ItemService();
        productService = new ProductService();
        product = productService.createProduct(CategoryEnum.BOOK, OriginEnum.LOCAL, BigDecimal.valueOf(12.49), "livres");
    }

    /**
     * Should throw exception when create product with quantity -1.
     *
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldThrowQuantityException() throws IllegalQuantityException {
        assertThatThrownBy(() -> itemService.createItem(product, -12))
                .isInstanceOf(IllegalQuantityException.class)
                .hasMessageContaining("Quantity should be higher than 1");
    }

    /**
     * Should create item.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldCreateItem() throws IllegalQuantityException {
        assertThat(itemService.createItem(product, 5)).isNotNull();
    }

    /**
     * Should get item ttc price.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetItemTtcPrice() throws IllegalPriceException, IllegalQuantityException {
        Item bookItem = itemService.createItem(product, 2);
        assertThat(bookItem.calculateTtcPrice().doubleValue()).isEqualTo(27.5);
    }

    @Test
    public void shouldGetItemTaxes() throws IllegalPriceException, IllegalQuantityException {
        Item bookItem = itemService.createItem(product, 2);
        assertThat(bookItem.calculateTaxes().doubleValue()).isEqualTo(2.5);
    }

    /**
     * Should get printed item line.
     *
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetPrintedItemLine() throws IllegalQuantityException {

        String expected = ("* 2 livres à 12.49€ : 27.50€ TTC\n");
        Item bookItem = itemService.createItem(product, 2);
        assertThat(bookItem.itemPrinter()).isEqualToIgnoringCase(expected);
    }

}
