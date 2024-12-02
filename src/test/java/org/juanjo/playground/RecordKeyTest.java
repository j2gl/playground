package org.juanjo.playground;

import org.juanjo.playground.domain.misc.BookId;
import org.juanjo.playground.domain.misc.AuthorId;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * This test is to show how to use records as keys in a Set
 * and how to compare them using the equals method
 */
class RecordKeyTest {

    private record RecordKey(
            AuthorId authorId,
            BookId bookId
    ) {
    }

    private static final UUID UUID_1 = UUID.randomUUID();
    private static final UUID UUID_2 = UUID.randomUUID();


    // Two records are equal if they have the same data
    @Test
    void testEquals() {
        var authorId1 = new AuthorId(UUID_1.toString());
        var bookId1 = new BookId(UUID_2.toString());
        var key1 = new RecordKey(authorId1, bookId1);

        var authorId2 = new AuthorId(UUID_1.toString());
        var bookId2 = new BookId(UUID_2.toString());
        var key2 = new RecordKey(authorId2, bookId2);
        assertThat(key1).isEqualTo(key2);

        Set<RecordKey> set = new HashSet<>();
        set.add(key1);
        set.add(key2);
        assertThat(set).hasSize(1);
    }

    // Two records are not equal if they have anything different
    @Test
    void testEquals_whenDifferent_shouldNotBeEqual() {
        var authorId1 = new AuthorId(UUID_1.toString());
        var bookId1 = new BookId(UUID_2.toString());
        var key1 = new RecordKey(authorId1, bookId1);

        var authorId2 = new AuthorId(UUID_1.toString());
        var bookId2 = new BookId();
        var key2 = new RecordKey(authorId2, bookId2);

        assertThat(key1).isNotEqualTo(key2);

        Set<RecordKey> set = new HashSet<>();
        set.add(key1);
        set.add(key2);
        assertThat(set).hasSize(2);
    }

}
