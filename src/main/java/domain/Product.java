package domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import util.RounderUtil;

import java.math.BigDecimal;

@Data
@Builder
@Slf4j
public class Product {
    private int quantity;
    private String name;
    private Category category;
    private BigDecimal htPrice;
    private BigDecimal ttcPrice;
    private BigDecimal taxes;
    private boolean isImported;

    public BigDecimal calculateTaxes(){
        BigDecimal tax=BigDecimal.valueOf((category.getTax() * htPrice.doubleValue() / 100));
        if(isImported)
            tax=tax.add(BigDecimal.valueOf((5 * htPrice.doubleValue() / 100)));
        this.taxes=tax.multiply(BigDecimal.valueOf(quantity));
        return this.taxes;
    }

    public BigDecimal calculateTtcPrice(){
        this.ttcPrice=(taxes.add(htPrice)).multiply(BigDecimal.valueOf(quantity));
        return this.ttcPrice;
    }

}
