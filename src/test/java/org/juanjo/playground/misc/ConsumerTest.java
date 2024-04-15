package org.juanjo.playground.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.IntConsumer;

@Slf4j
class ConsumerTest {

    private void runConsumer() {

        IntConsumer consumer1 = i -> log.info("Consumer1 {} OK", i);
        IntConsumer consumer2 = consumer1.andThen(i -> log.info("Consumer2 {} OK", i));

        for (int i = 0; i < 10; i++) {
            consumer2.accept(i);
        }
        log.info("Done consuming.");
    }

    @Test
    void testConsumer() {
        log.info("Start {}", this.getClass().getSimpleName());
        runConsumer();
        log.info("End of {}", this.getClass().getSimpleName());
    }

}
