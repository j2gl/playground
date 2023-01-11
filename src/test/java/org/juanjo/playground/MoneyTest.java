package org.juanjo.playground;

import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class MoneyTest {

    private static final Long LONG_AMOUNT = 12345L;
    private static final String CURRENCY = "EUR";

    private Money eur(double amount) {
        return Money.of(amount, CURRENCY);
    }

    @Test
    void testMoneyRoundingWithoutScale() {
        var money = Money.of(
                new BigDecimal(LONG_AMOUNT)
                        .divide(BigDecimal.valueOf(100L), RoundingMode.HALF_EVEN), CURRENCY);
        var expected = eur(LONG_AMOUNT / 100d);
        log.info("money = {}", money);
        log.info("expected = {}", expected);
        // without scale, decimal data is lost
        assertThat(money)
                .isNotEqualTo(expected)
                .isEqualTo(eur(123.00d));
    }

    @Test
    void testMoneyRoundingWithScale() {
        var money = Money.of(
                new BigDecimal(LONG_AMOUNT)
                        .divide(BigDecimal.valueOf(100L), 4, RoundingMode.HALF_EVEN), CURRENCY);
        var expected = eur(LONG_AMOUNT / 100d);
        log.info("money = {}", money);
        log.info("expected = {}", expected);
        assertThat(money).isEqualTo(expected);
    }

    @Test
    void givenAmountInJPY_whenSetMoney_thenReturnValueWithoutCents() {
        CurrencyUnit currency = Monetary.getCurrency("JPY");

        Money money = Money.ofMinor(currency, 125_321L);

        BigDecimal value = money.getNumberStripped();
        assertThat(value).isEqualTo(BigDecimal.valueOf(125_321L));
        assertThat(money)
                .isEqualTo(Money.of(125_321L, "JPY"))
                .hasToString("JPY 125321.00");
    }

    @Test
    void givenAmountInEUR_whenSetMoney_thenReturnValueWithCorrectFraction() {
        CurrencyUnit currency = Monetary.getCurrency("EUR");

        Money money = Money.ofMinor(currency, 125_321L);

        BigDecimal value = money.getNumberStripped();
        assertThat(value).isEqualTo(BigDecimal.valueOf(1253.21));
        assertThat(money).isEqualTo(Money.of(1253.21, "EUR"))
                .hasToString("EUR 1253.21");
    }
}
