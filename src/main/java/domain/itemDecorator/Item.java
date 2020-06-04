package domain.itemDecorator;

import java.math.BigDecimal;

/**
 * The interface Item.
 */
public interface Item {

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    int getQuantity();

    /**
     * Gets taxes.
     *
     * @return the taxes
     */
    BigDecimal getTaxes();

    /**
     * Gets ht price.
     *
     * @return the ht price
     */
    BigDecimal getHtPrice();

}
