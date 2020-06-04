package domain;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * The type Shopping cart.
 */
@Data
@Builder
public class ShoppingCart {
    private BigDecimal totalPrices;
    private BigDecimal totalTaxes;
    private List<Product> products;

    /**
     * Add product to cart.
     *
     * @param product the product
     */
    public void addProductToCart(Product product) {
        products.add(product);
        this.totalTaxes=totalTaxes.add(product.getTaxes());
        this.totalPrices=totalPrices.add(product.getTtcPrice());
    }

    /**
     * Add products to cart.
     *
     * @param products the products
     */
    public void addProductsToCart(List<Product> products){
        products.addAll(products);
        BigDecimal taxes=products.stream()
                .map(Product::getTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal price=products.stream()
                .map(Product::getTtcPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalTaxes= totalTaxes.add(taxes);
        this.totalPrices=totalPrices.add(price);
    }


}
