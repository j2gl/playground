package org.juanjo.playground.domain.misc;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import org.juanjo.playground.domain.id.UuidEntityId;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
public class AuthorId extends UuidEntityId {

    public AuthorId(String uuid) {
        super(UUID.fromString(uuid));
    }

    private AuthorId(UUID id) {
        super(id);
    }

    public static AuthorId fromUUID(UUID uuid) {
        return new AuthorId(uuid);
    }

}


