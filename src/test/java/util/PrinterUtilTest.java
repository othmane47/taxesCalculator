package util;

import domain.Category;
import domain.ShoppingBasket;
import domain.itemDecorator.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ItemService;
import service.ShoppingBasketService;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PrinterUtilTest {
    private static ShoppingBasketService shoppingBasketService;
    private static ItemService itemService;
    private static Item item1, item2, item3;
    private static ShoppingBasket shoppingBasket;
    private static PrinterUtil printerUtil;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        shoppingBasketService = new ShoppingBasketService();
        itemService = new ItemService();
    }

    @BeforeEach
    public void init() {
        item1 = itemService.createItem(Category.BOOK, false, BigDecimal.valueOf(12.49), "livres", 2);
        item2 = itemService.createItem(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD", 1);
        item3 = itemService.createItem(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        shoppingBasket = ShoppingBasket.builder()
                .items(new ArrayList<>())
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
        String invoice = PrinterUtil.invoicePrinter(shoppingBasket);
        assertThat(invoice).isEqualToIgnoringCase(expected);
    }

    @Test
    void shouldPrintItemLine() {
        String expected = ("* 2 livres à 12.49€ : 27.50€ TTC\n");
        String invoice = PrinterUtil.itemPrinter(item1).toString();
        assertThat(invoice).isEqualToIgnoringCase(expected);
    }

}
