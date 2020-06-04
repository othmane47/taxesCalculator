package domain.itemDecorator;

import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class ImportedItem extends ItemDecorator {
    static final int IMPORTED_TAX = 5;
    public ImportedItem(Item item) {
        super(item);
    }
    @Override
    public BigDecimal getTaxes() {
        BigDecimal tax=BigDecimal.valueOf(IMPORTED_TAX * item.getHtPrice().doubleValue() / 100);

        return item.getTaxes()
                .add(RounderUtil.roundAmountToTheNearestFiveCents(tax));

    }
}
