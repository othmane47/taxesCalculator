package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The type Rounder util.
 */
public class RounderUtil {

    /**
     * Round amount to the nearest five cents big decimal.
     *
     * @param taxes the taxes
     * @return the big decimal
     */
    public static BigDecimal roundAmountToTheNearestFiveCents(BigDecimal taxes) {
        return taxes
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05))
                .setScale(2);
    }
}
