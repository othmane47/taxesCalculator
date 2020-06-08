package service;

import domain.Product;
import enums.CategoryEnum;
import enums.OriginEnum;
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
     * Should throw exception when create product with price -10.
     *
     * @throws IllegalPriceException the illegal price exception
     */
    @Test
    public void shouldThrowPriceException() throws IllegalPriceException {
        assertThatThrownBy(() -> productService.createProduct(CategoryEnum.FOOD, OriginEnum.LOCAL, BigDecimal.valueOf(-100), "chocolat"))
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
        Product foodProduct = productService.createProduct(CategoryEnum.FOOD, OriginEnum.LOCAL, BigDecimal.valueOf(1), "chocolat");
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
        Product foodProduct = productService.createProduct(CategoryEnum.FOOD, OriginEnum.IMPORTED, BigDecimal.valueOf(1), "chocolat");
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
        Product bookProduct = productService.createProduct(CategoryEnum.BOOK, OriginEnum.LOCAL, BigDecimal.valueOf(1), "roman");
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
        Product bookProduct = productService.createProduct(CategoryEnum.BOOK, OriginEnum.IMPORTED, BigDecimal.valueOf(1), "roman");
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
        Product genericProduct = productService.createProduct(CategoryEnum.GENERIC, OriginEnum.LOCAL, BigDecimal.valueOf(1), "parfum");
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
        Product genericProduct = productService.createProduct(CategoryEnum.GENERIC, OriginEnum.IMPORTED, BigDecimal.valueOf(1), "parfum");
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
        Product bookProduct = productService.createProduct(CategoryEnum.BOOK, OriginEnum.LOCAL, BigDecimal.valueOf(10), "livre");
        assertThat(bookProduct.calculateTtcPrice().doubleValue()).isEqualTo(11.0);
    }

    /**
     * Should get imported book ttc price.
     *
     * @throws IllegalPriceException    the illegal price exception
     * @throws IllegalQuantityException the illegal quantity exception
     */
    @Test
    public void shouldGetImportedBookTtcPrice() throws IllegalPriceException, IllegalQuantityException {
        Product bookProduct = productService.createProduct(CategoryEnum.BOOK, OriginEnum.IMPORTED, BigDecimal.valueOf(10), "livre");
        assertThat(bookProduct.calculateTtcPrice().doubleValue()).isEqualTo(11.5);
    }


}
