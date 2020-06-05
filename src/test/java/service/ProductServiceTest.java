package service;

import domain.Category;
import domain.Product;
import exception.IllegalPriceException;
import exception.IllegalQuantityException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * The type Product service test.
 */
@Slf4j
class ProductServiceTest {
    private static ProductService productService;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        productService = new ProductService();
    }

    /**
     * Should throw exception when create product with quantity -1.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldThrowQuantityException() throws IllegalPriceException, IllegalQuantityException {
        assertThatThrownBy(() -> productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(1), "chocolat", -1))
                .isInstanceOf(IllegalQuantityException.class)
                .hasMessageContaining("Quantity should be higher than 1");
    }

    /**
     * Should throw exception when create product with price -10.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldThrowPriceException() throws IllegalPriceException, IllegalQuantityException {
        assertThatThrownBy(() -> productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(-100), "chocolat", 1))
                .isInstanceOf(IllegalPriceException.class)
                .hasMessageContaining("Price should be higher than 0€");
    }


    /**
     * Should get food tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetFoodTax() throws IllegalPriceException, IllegalQuantityException {
        Product foodProduct = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(foodProduct.calculateTaxes().doubleValue()).isEqualTo(0);
    }

    /**
     * Should get imported food tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedFoodTax() throws IllegalPriceException, IllegalQuantityException {
        Product foodProduct = productService.createProduct(Category.FOOD, true, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(foodProduct.calculateTaxes().doubleValue()).isEqualTo(0.05);
    }

    /**
     * Should get book tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetBookTax() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(1), "roman", 1);
        assertThat(bookProduct.calculateTaxes().doubleValue()).isEqualTo(0.10);
    }

    /**
     * Should get imported book tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedBookTax() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.BOOK, true, BigDecimal.valueOf(1), "roman", 1);
        assertThat(bookProduct.calculateTaxes().doubleValue()).isEqualTo(0.15);
    }

    /**
     * Should get generic tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetGenericTax() throws IllegalPriceException, IllegalQuantityException {
        Product genericProduct = productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(genericProduct.calculateTaxes().doubleValue()).isEqualTo(0.20);
    }

    /**
     * Should get imported generic tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedGenericTax() throws IllegalPriceException, IllegalQuantityException {
        Product genericProduct = productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(genericProduct.calculateTaxes().doubleValue()).isEqualTo(0.25);
    }

    /**
     * Should get book ttc price.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetBookTtcPrice() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(10), "livre", 2);
        assertThat(bookProduct.calculateTtcPrice().doubleValue()).isEqualTo(22.0);
    }

    /**
     * Should get imported book ttc price.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedBookTtcPrice() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.BOOK, true, BigDecimal.valueOf(10), "livre", 2);
        assertThat(bookProduct.calculateTtcPrice().doubleValue()).isEqualTo(23);
    }

    /**
     * Should get product line printed in the invoice.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetProductLine() throws IllegalPriceException, IllegalQuantityException {

        String expected = ("* 2 livres à 12.49€ : 27,50€ TTC\n");
        Product bookProduct = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(12.49), "livres", 2);
        assertThat(bookProduct.productPrinter()).isEqualToIgnoringCase(expected);
    }


}
