package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RounderUtil {

    public static BigDecimal roundAmountToTheNearestFiveCents(BigDecimal taxes) {
        return taxes
                .divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(0.05))
                .setScale(2);
    }
}
