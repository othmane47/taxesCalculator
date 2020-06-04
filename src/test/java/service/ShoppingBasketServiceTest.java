package service;

import domain.Category;
import domain.ShoppingBasket;
import domain.itemDecorator.BasicItem;
import domain.itemDecorator.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Shopping basket service test.
 */
class ShoppingBasketServiceTest {
    private static ShoppingBasketService shoppingBasketService;
    private static ItemService itemService;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {

        shoppingBasketService = new ShoppingBasketService();
        itemService = new ItemService();
    }

    /**
     * Should add item to bsket.
     */
    @Test
    public void shouldAddItemToBsket() {
        Item basicItem = BasicItem.builder()
                .htPrice(BigDecimal.valueOf(12.49))
                .name("chocolat")
                .quantity(1)
                .build();
        ShoppingBasket shoppingBasket = ShoppingBasket.builder()
                .items(new ArrayList<>())
                .build();
        assertThat(shoppingBasketService.addItemToBasket(shoppingBasket, basicItem).getItems().size()).isEqualTo(1);
    }

    /**
     * Should get basket total tax.
     */
    @Test
    public void shouldGetBasketTotalTax() {
        Item item1 = itemService.createItem(Category.BOOK, false, BigDecimal.valueOf(12.49), "roman", 2);
        Item item2 = itemService.createItem(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD", 1);
        Item item3 = itemService.createItem(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        ShoppingBasket shoppingBasket = ShoppingBasket.builder()
                .items(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .build();
        shoppingBasketService.addItemToBasket(shoppingBasket, item1);
        shoppingBasketService.addItemToBasket(shoppingBasket, item2);
        shoppingBasketService.addItemToBasket(shoppingBasket, item3);

        assertThat(shoppingBasket.getTotalTaxes()).isEqualTo(BigDecimal.valueOf(5.50).setScale(2));
    }


    /**
     * Should get basket total price.
     */
    @Test
    public void shouldGetBasketTotalPrice() {
        Item item1 = itemService.createItem(Category.BOOK, false, BigDecimal.valueOf(12.49), "roman", 2);
        Item item2 = itemService.createItem(Category.GENERIC, false, BigDecimal.valueOf(14.99), "CD", 1);
        Item item3 = itemService.createItem(Category.FOOD, false, BigDecimal.valueOf(0.85), "chocolat", 3);
        ShoppingBasket shoppingBasket = ShoppingBasket.builder()
                .items(new ArrayList<>())
                .totalTaxes(BigDecimal.ZERO)
                .totalPrices(BigDecimal.ZERO)
                .build();
        shoppingBasketService.addItemToBasket(shoppingBasket, item1);
        shoppingBasketService.addItemToBasket(shoppingBasket, item2);
        shoppingBasketService.addItemToBasket(shoppingBasket, item3);

        assertThat(shoppingBasket.getTotalPrices()).isEqualTo(BigDecimal.valueOf(48.02).setScale(2));

    }
}
