package util;

import domain.ShoppingBasket;
import domain.itemDecorator.Item;
import lombok.extern.slf4j.Slf4j;

/**
 * The Invoice Printer util.
 */
@Slf4j
public abstract class PrinterUtil {


    public static String invoicePrinter(ShoppingBasket shoppingBasket) {

        return " ";
    }

    public static StringBuilder itemPrinter(Item item) {
        StringBuilder itemLine = new StringBuilder("* ")
                .append(item.getQuantity())
                .append(" " + item.getName())
                .append(" à " + item.getHtPrice().setScale(2) + "€")
                .append(" : " + item.getTtcPrice().setScale(2) + "€ TTC\n");
        return itemLine;

    }

}
