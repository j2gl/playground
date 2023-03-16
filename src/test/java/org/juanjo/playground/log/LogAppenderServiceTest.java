package org.juanjo.playground.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LogAppenderServiceTest {

    private final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    private final LogAppenderService logAppenderService = new LogAppenderService();

    @BeforeEach
    void setUp() {
        logger.addAppender(listAppender);
        listAppender.start();
    }

    @AfterEach
    void tearDown() {
        listAppender.stop();
    }

    @Test
    void logInfoMessage() {
        // given
        var uuid = UUID.randomUUID();
        var message = "This is an info log message; uuid={}";

        // when
        logAppenderService.logInfoMessage(message, uuid);

        // then
        var expectedMessage = "This is an info log message; uuid=%s".formatted(uuid);
        containsLogMessage(listAppender, Level.INFO, expectedMessage);
    }

    @Test
    void logErrorMessage() {
        // given
        var uuid = UUID.randomUUID();
        var message = "This is an error log message; uuid={}";
        listAppender.start();

        // when
        logAppenderService.logErrorMessage(message, uuid);

        // then
        listAppender.stop();

        var expectedMessage = "This is an error log message; uuid=%s".formatted(uuid);
        containsLogMessage(listAppender, Level.ERROR, expectedMessage);
    }


    private void containsLogMessage(ListAppender<ILoggingEvent> listAppender, Level level, String message) {
        var messages = listAppender.list.stream()
                .map(entry -> Pair.of(entry.getLevel(), entry.getFormattedMessage()))
                .collect(Collectors.toSet());
        System.out.println("messages = " + messages);
        var log = messages.stream()
                .filter(eventMessage -> eventMessage.getRight().contains(message))
                .findFirst();
        
        var errorMessage = "Message [%s] with level [%s] not found. Following messages were present: %s"
                .formatted(message, level, messages);

        assertThat(log).withFailMessage(errorMessage).isPresent();
        log.ifPresent(entry -> assertThat(entry.getLeft()).isEqualTo(level));
    }

}
