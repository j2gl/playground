package org.juanjo.playground.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.domain.maps.Room;
import org.juanjo.playground.props.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class RoomController {

    private final PropertyService propertyService;

    @GetMapping("/rooms")
    public ResponseEntity<Map<Integer, Room>> getRooms() {
        log.debug("GET /rooms");
        return ResponseEntity.ok(propertyService.getRoomMap());
    }

    @GetMapping("/rooms/{roomNumber}")
    public ResponseEntity<Room> getRoom(@PathVariable Integer roomNumber) {
        log.debug("GET /rooms/{}", roomNumber);
        return ResponseEntity.ok(propertyService.getRoomMap().get(roomNumber));
    }

}
