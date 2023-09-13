package org.juanjo.playground.props;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.juanjo.playground.domain.maps.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@ConfigurationProperties(prefix = "playground.property-service")
public class PropertyService {

    private static final String PROPERTY_NAME = "playground.property-service.strings";

    @Value("${" + PROPERTY_NAME +":}")
    private Set<String> stringSet;
    @Value("${" + PROPERTY_NAME +":}")
    private List<String> stringList;

    @Getter
    @Setter
    private Map<Integer, Room> roomMap;

    @Scheduled(fixedRate = 600_000)
    public void showDataOnLog() {
        log.info("""
            Showing values from "{}" property stored as List and a Set:
            \tString Set  = {}, class={}
            \tString List = {}, class={}""",
                PROPERTY_NAME, stringSet, stringSet.getClass(), stringList, stringList.getClass());
    }

    public Set<String> getPropertyAsSet() {
        return stringSet;
    }

    public List<String> getPropertyAsList() {
        return stringList;
    }

}
