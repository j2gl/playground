package org.juanjo.playground;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class RepeatedFlakyTest {

    @RepeatedTest(value = 10)
    @Disabled
    void checkNumberIsEven_flakyTest() {
        int number = (int) (Math.random() * 1000);
        log.info("Is {} an even number?", number);
        assertThat(number % 2).isZero();
    }
}
