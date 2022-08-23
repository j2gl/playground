package org.juanjo.playground;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Slf4j
class CurrenciesTest {

    public static Set<Currency> currencies;

    @BeforeAll
    public static void init() {
        currencies = new HashSet<>();
        Locale[] locales = Locale.getAvailableLocales();
        Arrays.stream(locales).toList()
                .stream()
                .filter(s -> s != null  && !s.toString().isBlank())
                .forEach(locale -> {
                            try {
                                Currency currency = Currency.getInstance(locale);
                                currencies.add(currency);
                            } catch (IllegalArgumentException ignore) {
                                // ignore expected exceptions
                            }
                        }
                );
    }

    @Test
    void showCurrenciesWithoutFractionalDigits() {
        log.info("*** Show currencies without fraction digits ***");
        currencies.stream()
                .filter(c -> c.getDefaultFractionDigits() <= 0)
                .sorted(Comparator.comparing(Currency::getCurrencyCode) )
                .forEach(c -> log.info("currencyCode={}, numericCode={}, displayName={}", c,
                        String.format("%03d",c.getNumericCode()),
                        c.getDisplayName()));
    }

    @Test
    void showAllCurrencies() {
        log.info("*** Show all currencies ***");
        currencies.stream()
                .sorted(Comparator.comparing(Currency::getCurrencyCode) )
                .forEach(
                c -> log.info("currencyCode={}, numericCode={}, symbol={}, displayName={}", c,
                        String.format("%03d",c.getNumericCode()),
                        String.format("%-3s",c.getSymbol()),
                        c.getDisplayName()));
    }
}
