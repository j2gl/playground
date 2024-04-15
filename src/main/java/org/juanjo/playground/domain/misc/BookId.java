package org.juanjo.playground.domain.misc;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import org.juanjo.playground.domain.id.UuidEntityId;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
public class BookId extends UuidEntityId {

    public BookId(String uuid) {
        super(UUID.fromString(uuid));
    }

    private BookId(UUID id) {
        super(id);
    }

    public static BookId fromUUID(UUID uuid) {
        return new BookId(uuid);
    }

}
