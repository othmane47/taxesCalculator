package domain;

import enums.CategoryEnum;
import enums.OriginEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
@Slf4j
@Builder
public class Product {
    private String name;
    private CategoryEnum categoryEnum;
    private OriginEnum originEnum;
    private BigDecimal htPrice;
    @Getter(lazy = true)
    private final BigDecimal taxes = calculateTaxes();
    @Getter(lazy = true)
    private final BigDecimal ttcPrice = calculateTtcPrice();

    /**
     * Calculate taxes big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTaxes() {
        return htPrice.multiply(categoryEnum.getTax().add(originEnum.getTax()));
    }

    /**
     * Calculate ttc price big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTtcPrice() {
        return getTaxes().add(htPrice);
    }


}
