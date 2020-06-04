package util;

import domain.Product;
import domain.ShoppingCart;
import lombok.extern.slf4j.Slf4j;

/**
 * The Invoice Printer util.
 */
@Slf4j
public class PrinterUtil {

    public static String invoicePrinter(ShoppingCart shoppingCart) {

        return " ";
    }

    public static StringBuilder productPrinter(Product product) {
        return new StringBuilder("* ")
                .append(product.getQuantity())
                .append(" " + product.getName())
                .append(" à " + product.getHtPrice()+ "€")
                .append(" : " + product.getTtcPrice() + "€ TTC\n");

    }

}
