package domain.itemDecorator;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The type Basic item.
 */
@Data
@Builder
public class BasicItem implements Item {
    private int quantity;
    private String name;
    private BigDecimal htPrice;
    private BigDecimal taxes;

    @Override
    public int getQuantity() {
        return  this.quantity;
    }
    @Override
    public BigDecimal getTaxes() {
        return  BigDecimal.ZERO;
    }
    @Override
    public BigDecimal getHtPrice() {
        return this.htPrice;
    }
}
