package domain.itemDecorator;

import util.RounderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookItem extends ItemDecorator {
    static final int BOOK_TAX = 10;

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
