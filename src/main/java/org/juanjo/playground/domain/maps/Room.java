package org.juanjo.playground.domain.maps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Room {

    private final Integer roomNumber;
    private final Integer floor;
    private final String guestName;
    private final Boolean isOccupied;

}
