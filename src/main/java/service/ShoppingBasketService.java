package service;


import domain.ShoppingBasket;
import domain.itemDecorator.Item;
import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;

@Slf4j
public class ShoppingBasketService {

    public ShoppingBasket addItemToBasket(ShoppingBasket shoppingBasket, Item item) {
        BigDecimal totalTaxes=shoppingBasket.getTotalTaxes()
                                            .add(item.getTaxes()
                                                    .multiply(BigDecimal
                                                            .valueOf(item.getQuantity())));
        BigDecimal totalPrice=shoppingBasket.getTotalPrices()
                                .add(item.getHtPrice()
                                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                                            .add(totalTaxes);
        shoppingBasket.setTotalTaxes(totalTaxes);
        shoppingBasket.setTotalPrices(totalPrice);
        shoppingBasket.getItems().add(item);
        return shoppingBasket;
    }


}
