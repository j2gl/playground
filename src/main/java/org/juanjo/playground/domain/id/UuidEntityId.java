package org.juanjo.playground.domain.id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.UUID;


@Getter
@MappedSuperclass
@EqualsAndHashCode
@JsonSerialize(using = ToStringSerializer.class)
public abstract class UuidEntityId implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    private final UUID value;

    protected UuidEntityId() {
        this.value = UUID.randomUUID();
    }

    protected UuidEntityId(UUID value) {
        Assert.notNull(value, "The id value cannot be null or empty");
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
