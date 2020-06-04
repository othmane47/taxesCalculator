package domain;

import domain.itemDecorator.Item;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Shopping basket.
 */
@Data
@Builder
public class ShoppingBasket {
    private BigDecimal totalPrices;
    private BigDecimal totalTaxes;
    private List<Item> items;


    /**
     * Calculate taxes big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal calculateTaxes() {
        return items.stream()
                .map(Item::getTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
