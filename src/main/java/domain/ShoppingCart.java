package domain;

import lombok.Builder;
import lombok.Data;
import util.RounderUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Shopping basket.
 */
@Data
@Builder
public class ShoppingCart {
    private BigDecimal totalPrices;
    private BigDecimal totalTaxes;
    private List<Product> products;



    public void addItemToCart(Product product) {
        products.add(product);
        this.totalTaxes=this.totalTaxes.add(product.getTaxes());
        this.totalPrices=this.totalPrices.add(product.getTtcPrice());
    }

    public void addItemsToCart(List<Product> products){
        products.addAll(products);
        BigDecimal taxes=products.stream()
                .map(Product::getTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal price=products.stream()
                .map(Product::getTtcPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalTaxes= RounderUtil.roundAmountToTheNearestFiveCents(this.totalTaxes.add(taxes));
        this.totalPrices=RounderUtil.roundAmountToTheNearestFiveCents(this.totalPrices.add(price));
    }


}
