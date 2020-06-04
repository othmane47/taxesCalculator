package domain.itemDecorator;

import util.RounderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GenericItem extends ItemDecorator {
    static final int GENERIC_TAX = 20;

    public GenericItem(Item item) {
        super(item);
    }

    @Override
    public BigDecimal getTaxes() {
        BigDecimal tax=BigDecimal.valueOf(GENERIC_TAX * item.getHtPrice().doubleValue() / 100);

        return item.getTaxes()
                .add(RounderUtil.roundAmountToTheNearestFiveCents(tax));
    }

}
