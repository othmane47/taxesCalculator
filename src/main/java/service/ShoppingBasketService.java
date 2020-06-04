package service;


import domain.ShoppingBasket;
import domain.itemDecorator.Item;
import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;

/**
 * The type Shopping basket service.
 */
@Slf4j
public class ShoppingBasketService {

    /**
     * Add item to basket shopping basket.
     *
     * @param shoppingBasket the shopping basket
     * @param item           the item
     * @return the shopping basket
     */
    public ShoppingBasket addItemToBasket(ShoppingBasket shoppingBasket, Item item) {
        BigDecimal totalTaxes=shoppingBasket.getTotalTaxes()
                                            .add(item.getTaxes()
                                                    .multiply(BigDecimal
                                                            .valueOf(item.getQuantity())));
        BigDecimal totalPrice=shoppingBasket.getTotalPrices()
                                .add((item.getHtPrice())
                                        .add(item.getTaxes())
                                        .multiply(BigDecimal
                                                .valueOf(item.getQuantity())));
        shoppingBasket.setTotalTaxes(totalTaxes);
        shoppingBasket.setTotalPrices(totalPrice);
        shoppingBasket.getItems().add(item);
        return shoppingBasket;
    }


}
