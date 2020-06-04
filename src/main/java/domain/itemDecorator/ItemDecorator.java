package domain.itemDecorator;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * The type Item decorator.
 */
@Data
public class ItemDecorator implements Item {
    /**
     * The Item.
     */
    protected Item item;

    /**
     * Instantiates a new Item decorator.
     *
     * @param item the item
     */
    public ItemDecorator(Item item) {
        this.item = item;
    }

    @Override
    public int getQuantity() {return item.getQuantity();}

    @Override
    public BigDecimal getTaxes() {
        return item.getTaxes();
    }

    @Override
    public BigDecimal getHtPrice() {
        return item.getHtPrice();
    }
}
