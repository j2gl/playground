package org.juanjo.playground.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTest {

    private boolean isFruit(String fruitName) {
        if (fruitName.equals("apple")) {
            return true;
        } else if (fruitName.equals("banana")) {
            return true;
        } else if (fruitName.equals("orange")) {
            return true;
        } else {
            throw new IllegalArgumentException("%s is not a fruit.".formatted(fruitName));
        }
    }

    @Test
    void isFruit_whenInvalidFruit_shouldThrowException() {
        // given
        var noFruit = "tomato";

        // when
        var exception = assertThatThrownBy(() -> isFruit(noFruit));

        // then
        exception.isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s is not a fruit.".formatted(noFruit));
    }

}
