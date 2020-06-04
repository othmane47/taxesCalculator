package domain.itemDecorator;

import util.RounderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The type Generic item.
 */
public class GenericItem extends ItemDecorator {
    /**
     * The Generic tax.
     */
    static final int GENERIC_TAX = 20;

    /**
     * Instantiates a new Generic item.
     *
     * @param item the item
     */
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
