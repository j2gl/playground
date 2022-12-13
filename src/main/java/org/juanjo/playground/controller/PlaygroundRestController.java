package org.juanjo.playground.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.props.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PlaygroundRestController {

    private final PropertyService propertyService;

    @GetMapping("/properties/list")
    public ResponseEntity<List<String>> getPropertyAsList() {
        var property = propertyService.getPropertyAsList();
        log.debug("Property as List: {}", property);
        return ResponseEntity.ok(propertyService.getPropertyAsList());
    }

    @GetMapping("/properties/set")
    public ResponseEntity<Set<String>> getPropertyAsSet(HttpServletRequest request) {
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                log.debug("Header {}: {}", header, request.getHeader(header)));
        var property = propertyService.getPropertyAsSet();
        log.debug("Property as Set: {}", property);
        return ResponseEntity.ok(property);
    }

}
