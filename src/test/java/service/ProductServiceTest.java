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
     * Should get food tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetFoodTax() throws IllegalPriceException , IllegalQuantityException{
        Product foodProduct = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(foodProduct.getTaxes().doubleValue()).isEqualTo(0);
    }

    /**
     * Should get imported food tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedFoodTax() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.FOOD, true, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.05);
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
        log.info(bookProduct.getTaxes().toString());
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.10);
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
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.15);
    }

    /**
     * Should get generic tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetGenericTax()  throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.20);
    }

    /**
     * Should get imported generic tax.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedGenericTax() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.25);
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
        assertThat(bookProduct.getTtcPrice().doubleValue()).isEqualTo(22.0);
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
        assertThat(bookProduct.getTtcPrice().doubleValue()).isEqualTo(23);
    }



}
