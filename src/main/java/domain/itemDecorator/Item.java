package domain.itemDecorator;

import java.math.BigDecimal;

public interface Item {

    int getQuantity();

    BigDecimal getTaxes();

    BigDecimal getHtPrice();

}
