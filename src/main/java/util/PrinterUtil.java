package util;


import domain.Product;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Printer util.
 */
@Slf4j
public class PrinterUtil {

    /**
     * Invoice printer string.
     *
     * @param shoppingCart the shopping cart
     * @return the string
     */
    public static String invoicePrinter(ShoppingCart shoppingCart) {
        StringBuilder invoice = new StringBuilder();

        shoppingCart.getProducts()
                        .forEach(item -> {invoice.append(productPrinter(item));
                        log.info(productPrinter(item).toString());});

        invoice.append("\nMontant des taxes : " + shoppingCart.getTotalTaxes());
        invoice.append("\nTotal : " + shoppingCart.getTotalPrices());
        return invoice.toString();
    }

    /**
     * Product printer string builder.
     *
     * @param product the product
     * @return the string builder
     */
    public static StringBuilder productPrinter(Product product) {
        return new StringBuilder("* ")
                .append(product.getQuantity())
                .append(" " + product.getName())
                .append(product.isImported()?" importé":"")
                .append(" à " + product.getHtPrice()+ "€")
                .append(" : " + product.getTtcPrice() + "€ TTC\n");

    }

}
