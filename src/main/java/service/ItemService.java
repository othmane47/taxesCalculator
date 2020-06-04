package service;

import domain.Category;
import domain.itemDecorator.*;

import java.math.BigDecimal;

public class ItemService {


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
