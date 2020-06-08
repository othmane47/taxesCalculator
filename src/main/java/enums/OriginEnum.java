package enums;

import java.math.BigDecimal;

/**
 * The enum Origin enum.
 */
public enum OriginEnum {


    /**
     * Local origin enum.
     */
    LOCAL(BigDecimal.valueOf(0)),

    /**
     * Imported origin enum.
     */
    IMPORTED(BigDecimal.valueOf(0.05));

    private BigDecimal tax;

    OriginEnum(BigDecimal tax) {
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
