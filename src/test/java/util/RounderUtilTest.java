package util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class RounderUtilTest {

    @Test
    public void shouldRound0_99To1_00Cents() {
        assertThat(RounderUtil.roundAmountToTheNearestFiveCents(BigDecimal.valueOf(0.99))).isEqualTo(BigDecimal.valueOf(1.00).setScale(2));
    }

    @Test
    public void shouldNotRound1_00() {
        assertThat(RounderUtil.roundAmountToTheNearestFiveCents(BigDecimal.valueOf(1.00))).isEqualTo(BigDecimal.valueOf(1.00).setScale(2));
    }


    @Test
    public void shouldRound1_01To1_05Cents() {
        assertThat(RounderUtil.roundAmountToTheNearestFiveCents(BigDecimal.valueOf(1.01))).isEqualTo(BigDecimal.valueOf(1.05).setScale(2));
    }
}
