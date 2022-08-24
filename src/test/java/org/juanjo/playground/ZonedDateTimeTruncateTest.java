package org.juanjo.playground;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ZonedDateTimeTruncateTest {

    @Test
    void testZonedDateAreEqualIgnoringSeconds() {
        ZonedDateTime expected = ZonedDateTime.of(2022,1, 10,14,52,0, 582945000, ZoneId.of("UTC"));
        ZonedDateTime actual = ZonedDateTime.of(2022,1, 10,14,52,59, 582945233, ZoneId.of("UTC"));

        assertThat(actual).isEqualToIgnoringSeconds(expected);
    }

    @Test
    void testZonedDateAreEqual() {
        ZonedDateTime expected = ZonedDateTime.of(2022,1, 10,0,0,59, 582945000, ZoneId.of("UTC"));
        ZonedDateTime actual = ZonedDateTime.of(2022,1, 10,0,1,1, 582945233, ZoneId.of("UTC"));

        // assertThat(actual).isEqualToIgnoringSeconds(expected); // Will fail even if it's 1 second apart.
        assertThat(actual)
                .isBetween(expected.minus(10, SECONDS), expected.plus(10, SECONDS))
                .isCloseTo(expected, within(10, SECONDS));
    }

}
