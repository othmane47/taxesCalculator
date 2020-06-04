package util;

import domain.Category;
import domain.Product;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ProductService;
import service.ShoppingCartService;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PrinterUtilTest {
    private static ShoppingCartService shoppingCartService;
    private static ProductService productService;
    private static Product product1, product2, product3;
    private static ShoppingCart shoppingCart;
    private static PrinterUtil printerUtil;

    @BeforeAll
    public static void setUp() {
        shoppingCartService = new ShoppingCartService();
        productService = new ProductService();
    }

    @BeforeEach
    public void init() {
        product1 = productService.createProduct(Category.BOOK, false, BigDecimal.valueOf(12.49), "livres", 2);
        product2 = productService.createProduct(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD", 1);
        product3 = productService.createProduct(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        shoppingCart = ShoppingCart.builder()
                .products(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .totalPrices(BigDecimal.ZERO)
                .build();
    }

    @Test
    void invoicePrinter() {
        String expected = ("* 2 livres à 12.49€ : 27,5€ TTC\n" +
                "* 1 CD musical à 14.99€ : 18€ TTC\n" +
                "* 3 barres de chocolat à 0.85€ : 2.55€ TTC\n" +
                "\n" +
                "Montant des taxes : 5.53€\n" +
                "Total : 48.05€");
        String invoice = PrinterUtil.invoicePrinter(shoppingCart);
        assertThat(invoice).isEqualToIgnoringCase(expected);
    }

    @Test
    void shouldPrintProductLine() {
        String expected = ("* 2 livres à 12.49€ : 27.50€ TTC\n");
        String invoice = PrinterUtil.productPrinter(product1).toString();
        assertThat(invoice).isEqualToIgnoringCase(expected);
    }

}
