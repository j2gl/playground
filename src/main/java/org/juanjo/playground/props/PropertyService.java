package org.juanjo.playground.props;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PropertyService {

    private static final String PROPERTY_NAME = "playground.test.set-strings";
    @Value("${" + PROPERTY_NAME +":}")
    private Set<String> stringSet;
    @Value("${" + PROPERTY_NAME +":}")
    private List<String> stringList;

    @Scheduled(fixedRate = 5000)
    public void showData() {
        log.info("""
            Showing values from "{}" property stored as List and a Set:
            \tString Set  = {}, class={}
            \tString List = {}, class={}""",
                PROPERTY_NAME, stringSet, stringSet.getClass(), stringList, stringList.getClass());
    }

}
