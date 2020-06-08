package domain;

import enums.OriginEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import util.RounderUtil;

import java.math.BigDecimal;

/**
 * The type Item.
 */
@Data
@Builder
public class Item {
    private int quantity;
    private Product product;
    @Getter(lazy = true)
    private final BigDecimal ttcPrice = calculateTtcPrice();
    @Getter(lazy = true)
    private final BigDecimal taxes = calculateTaxes();


    /**
     * Calculate taxes big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTaxes() {
        return RounderUtil.roundAmountToTheNearestFiveCents(product.getTaxes()
                .multiply(BigDecimal.valueOf(quantity)));
    }

    /**
     * Calculate ttc price big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTtcPrice() {
        return RounderUtil.roundAmountToTheNearestFiveCents(product.getTtcPrice().multiply(BigDecimal.valueOf(quantity)));
    }


    /**
     * Product printer string builder.
     *
     * @return the string builder
     */
    public StringBuilder itemPrinter() {

        return new StringBuilder("* ")
                .append(quantity)
                .append(" " + product.getName())
                .append(product.getOriginEnum().equals(OriginEnum.IMPORTED) ? " importé" : "")
                .append(" à " + product.getHtPrice() + "€")
                .append(" : " + getTtcPrice() + "€ TTC\n");

    }
}
