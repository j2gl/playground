package org.juanjo.playground.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Sample class to have an example of how to do Unit Testing by checking the log output.
 * See LogAppenderServiceTest
 */
@Slf4j
@Service
public class LogAppenderService {

    public void logInfoMessage(String message, Object... args) {
        log.info(message, args);
    }

    public void logErrorMessage(String message, Object... args) {
        log.error(message, args);
    }

}
