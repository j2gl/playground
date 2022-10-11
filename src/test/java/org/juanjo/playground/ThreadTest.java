package org.juanjo.playground;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ThreadTest {

    @Test
    void testJoinThreads() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                if (i % 10 == 0) {
                    log.info("Step: " + i);
                }
            }
            log.info("Thread finished!");
        };
        var thread = new Thread(runnable);
        thread.start();

        assertThat(thread.isAlive()).isTrue();
        thread.join(); // Join will wait for thread to finish.

        assertThat(thread.isAlive()).isFalse();
        log.info("Main thread finished!");
    }

}
