package service;

import domain.Category;
import domain.itemDecorator.*;

import java.math.BigDecimal;

/**
 * The type Item service.
 */
public class ItemService {


    /**
     * Create item item.
     *
     * @param category   the category
     * @param isImported the is imported
     * @param htPrice    the ht price
     * @param name       the name
     * @param quantity   the quantity
     * @return the item
     */
    public Item createItem(Category category, boolean isImported, BigDecimal htPrice, String name, int quantity) {
        Item basicItem = BasicItem.builder()
                .htPrice(htPrice)
                .name(name)
                .quantity(quantity)
                .build();
        switch (category) {
            case FOOD:
            case MEDICAL:
                if (isImported)
                    return new ImportedItem(basicItem);
                return basicItem;
            case BOOK:
                if (isImported)
                    return new BookItem(new ImportedItem(basicItem));
                return new BookItem(basicItem);
            case GENERIC:
                if (isImported)
                    return new GenericItem(new ImportedItem(basicItem));
                return new GenericItem(basicItem);
        }
        return null;
    }

}
