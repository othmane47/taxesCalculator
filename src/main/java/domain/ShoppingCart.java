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
    private List<Product> products;

    /**
     * Add product to cart.
     *
     * @param product the product
     */
    public void addProductToCart(Product product) {
        products.add(product);
        this.totalTaxes = totalTaxes.add(product.getTaxes());
        this.totalPrices = totalPrices.add(product.getTtcPrice());
    }

    /**
     * Add products to cart.
     *
     * @param products the products
     */
    public void addProductsToCart(List<Product> products) {
        this.products.addAll(products);
        BigDecimal taxes = products.stream()
                .map(Product::getTaxes)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal price = products.stream()
                .map(Product::getTtcPrice)
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
        products.forEach(item -> invoice.append(item.productPrinter()));
        invoice.append("\nMontant des taxes : " + totalTaxes);
        invoice.append("\nTotal : " + totalPrices);
        log.info(invoice.toString());
        return invoice.toString();
    }


}
