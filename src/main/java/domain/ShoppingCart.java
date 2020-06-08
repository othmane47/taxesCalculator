package domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Shopping cart.
 */
@Data
@Builder
@Slf4j
public class ShoppingCart {
    private BigDecimal totalPrices;
    private BigDecimal totalTaxes;
    private List<Item> items;

    /**
     * Add item to cart.
     *
     * @param item the item
     */
    public void addItemToCart(Item item) {
        items.add(item);
        this.totalTaxes = totalTaxes.add(item.getTaxes());
        this.totalPrices = totalPrices.add(item.getTtcPrice());
    }

    /**
     * Add items to cart.
     *
     * @param items the items
     */
    public void addItemsToCart(List<Item> items) {
        this.items.addAll(items);
        BigDecimal taxes = items.stream()
                .map(Item::getTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal price = items.stream()
                .map(Item::getTtcPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalTaxes = totalTaxes.add(taxes);
        this.totalPrices = totalPrices.add(price);
    }

    /**
     * Invoice printer string.
     *
     * @return the string
     */
    public String invoicePrinter() {
        StringBuilder invoice = new StringBuilder("\n================ Invoice ================\n");
        items.forEach(item -> invoice.append(item.itemPrinter()));
        invoice.append("\nMontant des taxes : " + totalTaxes + "€");
        invoice.append("\nTotal : " + totalPrices + "€");
        log.info(invoice.toString());
        return invoice.toString();
    }


}
