package domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
@Builder
@Slf4j
public class Product {
    private int quantity;
    private String name;
    private Category category;
    private BigDecimal htPrice;
    private BigDecimal ttcPrice;
    private BigDecimal taxes;
    private boolean isImported;

    /**
     * Calculate taxes big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTaxes() {
        BigDecimal tax = RounderUtil.roundAmountToTheNearestFiveCents(htPrice.multiply(BigDecimal.valueOf(category.getTax())
                .divide(BigDecimal.valueOf(100))));

        if (isImported)
            tax = tax.add(RounderUtil.roundAmountToTheNearestFiveCents(htPrice.multiply(BigDecimal.valueOf(5)
                    .divide(BigDecimal.valueOf(100)))));

        this.taxes = tax.multiply(BigDecimal.valueOf(quantity));
        return this.taxes;
    }

    /**
     * Calculate ttc price big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTtcPrice() {
        this.ttcPrice = taxes.add(htPrice
                .multiply(BigDecimal.valueOf(quantity)));
        return this.ttcPrice;
    }

    /**
     * Product printer string builder.
     *
     * @return the string builder
     */
    public StringBuilder productPrinter() {

        return new StringBuilder("* ")
                .append(quantity)
                .append(" " + name)
                .append(isImported ? " importé" : "")
                .append(" à " + htPrice + "€")
                .append(" : " + ttcPrice + "€ TTC\n");

    }

}
