package domain.itemDecorator;

import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;

/**
 * The type Imported item.
 */
@Slf4j
public class ImportedItem extends ItemDecorator {
    /**
     * The Imported tax.
     */
    static final int IMPORTED_TAX = 5;

    /**
     * Instantiates a new Imported item.
     *
     * @param item the item
     */
    public ImportedItem(Item item) {
        super(item);
    }

    @Override
    public BigDecimal getTaxes() {
        BigDecimal tax = BigDecimal.valueOf(IMPORTED_TAX * item.getHtPrice().doubleValue() / 100);
        return item.getTaxes()
                .add(RounderUtil.roundAmountToTheNearestFiveCents(tax));
    }

    @Override
    public BigDecimal getTtcPrice() {
        BigDecimal ttcPrice = BigDecimal.valueOf(item.getQuantity())
                .multiply(getTaxes()
                        .add(item.getHtPrice()));
        return RounderUtil.roundAmountToTheNearestFiveCents(ttcPrice);

    }
}
