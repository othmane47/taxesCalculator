package service;

import domain.Category;
import domain.Product;
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
     */
    @Test
    public void shouldGetFoodTax() {
        Product foodProduct = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(foodProduct.getTaxes().doubleValue()).isEqualTo(0);
    }

    /**
     * Should get imported food tax.
     */
    @Test
    public void shouldGetImportedFoodTax() {
        Product bookProduct = productService.createProduct(Category.FOOD, true, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.05);
    }

    /**
     * Should get book tax.
     */
    @Test
    public void shouldGetBookTax() {
        Product bookProduct = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(1), "roman", 1);
        log.info(bookProduct.getTaxes().toString());
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.10);
    }

    /**
     * Should get imported book tax.
     */
    @Test
    public void shouldGetImportedBookTax() {
        Product bookProduct = productService.createProduct(Category.BOOK, true, BigDecimal.valueOf(1), "roman", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.15);
    }

    /**
     * Should get generic tax.
     */
    @Test
    public void shouldGetGenericTax() {
        Product bookProduct = productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.20);
    }

    /**
     * Should get imported generic tax.
     */
    @Test
    public void shouldGetImportedGenericTax() {
        Product bookProduct = productService.createProduct(Category.GENERIC, true, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookProduct.getTaxes().doubleValue()).isEqualTo(0.25);
    }

    @Test
    public void shouldGetBookTtcPrice() {
        Product bookProduct = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(10), "livre", 2);
        assertThat(bookProduct.getTtcPrice().doubleValue()).isEqualTo(22.0);
    }

    @Test
    public void shouldGetImportedBookTtcPrice() {
        Product bookProduct = productService.createProduct(Category.BOOK, true, BigDecimal.valueOf(10), "livre", 2);
        assertThat(bookProduct.getTtcPrice().doubleValue()).isEqualTo(23);
    }



}
