package domain.itemDecorator;

import util.RounderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The type Book item.
 */
public class BookItem extends ItemDecorator {
    /**
     * The Book tax.
     */
    static final int BOOK_TAX = 10;

    /**
     * Instantiates a new Book item.
     *
     * @param item the item
     */
    public BookItem(Item item) {
        super(item);
    }

    @Override
    public BigDecimal getTaxes() {
        BigDecimal tax=BigDecimal.valueOf(BOOK_TAX * item.getHtPrice().doubleValue() / 100);
        return item.getTaxes()
                .add(RounderUtil.roundAmountToTheNearestFiveCents(tax));
    }
}
