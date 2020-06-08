package enums;

import java.math.BigDecimal;

/**
 * The enum CategoryEnum.
 */
public enum CategoryEnum {
    /**
     * Book categoryEnum.
     */
    BOOK(BigDecimal.valueOf(0.1)),
    /**
     * Food categoryEnum.
     */
    FOOD(BigDecimal.valueOf(0)),
    /**
     * Medical categoryEnum.
     */
    MEDICAL(BigDecimal.valueOf(0)),
    /**
     * Generic categoryEnum.
     */
    GENERIC(BigDecimal.valueOf(0.2));


    private BigDecimal tax;

    CategoryEnum(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Gets tax.
     *
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }
}
