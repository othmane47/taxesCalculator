package service;

import domain.Category;
import domain.itemDecorator.BasicItem;
import domain.itemDecorator.BookItem;
import domain.itemDecorator.ImportedItem;
import domain.itemDecorator.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ItemServiceTest {
    private static ItemService itemService;

    /**
     * Sets up.
     */

    @BeforeAll
    public static void setUp() {
        itemService = new ItemService();
    }

    @Test
    public void shouldCreateFoodItem() {
        Item foodItem = itemService.createItem(Category.FOOD, false, BigDecimal.valueOf(12.49), "chocolat", 1);
        assertThat(foodItem).isInstanceOf(BasicItem.class);
    }

    @Test
    public void shouldCreateImportedItem() {
        Item importedItem = itemService.createItem(Category.FOOD, true, BigDecimal.valueOf(12.49), "chocolat", 1);
        assertThat(importedItem).isInstanceOf(ImportedItem.class);
    }

    @Test
    public void shouldCreateMedicalItem() {
        Item medicalItem = itemService.createItem(Category.MEDICAL, false, BigDecimal.valueOf(12.49), "doliprane", 1);
        assertThat(medicalItem).isInstanceOf(BasicItem.class);
    }

    @Test
    public void shouldCreateBookItem() {
        Item bookItem = itemService.createItem(Category.BOOK, false, BigDecimal.valueOf(12.49), "livres", 1);
        assertThat(bookItem).isInstanceOf(BookItem.class);
    }

    @Test
    public void shouldCreateGenericItem() {
        Item genericItem = itemService.createItem(Category.GENERIC, false, BigDecimal.valueOf(12.49), "parfum", 1);
    }

    @Test
    public void shouldGetFoodTax() {
        Item foodItem = itemService.createItem(Category.FOOD, false, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(foodItem.getTaxes().doubleValue()).isEqualTo(0);
    }

    @Test
    public void shouldGetImportedFoodTax() {
        Item bookItem = itemService.createItem(Category.FOOD, true, BigDecimal.valueOf(1), "chocolat", 1);
        assertThat(bookItem.getTaxes().doubleValue()).isEqualTo(0.05);
    }

    @Test
    public void shouldGetBookTax() {
        Item bookItem = itemService.createItem(Category.BOOK, false, BigDecimal.valueOf(1), "roman", 1);
        log.info(bookItem.getTaxes().toString());
        assertThat(bookItem.getTaxes().doubleValue()).isEqualTo(0.10);
    }

    @Test
    public void shouldGetImportedBookTax() {
        Item bookItem = itemService.createItem(Category.BOOK, true, BigDecimal.valueOf(1), "roman", 1);
        assertThat(bookItem.getTaxes().doubleValue()).isEqualTo(0.15);
    }

    @Test
    public void shouldGetGenericTax() {
        Item bookItem = itemService.createItem(Category.GENERIC, false, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookItem.getTaxes().doubleValue()).isEqualTo(0.20);
    }

    @Test
    public void shouldGetImportedGenericTax() {
        Item bookItem = itemService.createItem(Category.GENERIC, true, BigDecimal.valueOf(1), "parfum", 1);
        assertThat(bookItem.getTaxes().doubleValue()).isEqualTo(0.25);
    }


}
