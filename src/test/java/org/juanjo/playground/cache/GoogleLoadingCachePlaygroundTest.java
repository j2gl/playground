package org.juanjo.playground.cache;

import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.juanjo.playground.cache.GoogleLoadingCachePlayground.CACHE_SIZE;

class GoogleLoadingCachePlaygroundTest {

    @Test
    void getLoadingCacheTest_whenCacheChanged_thenFinish() throws ExecutionException {
        GoogleLoadingCachePlayground googleCache = new GoogleLoadingCachePlayground();

        googleCache.printValuesFromCache();
        googleCache.doSlowIntensiveOperation();

        var loadingCache = googleCache.getLoadingCache();
        assertThat(loadingCache.size()).isEqualTo(CACHE_SIZE);

        var initialList = getAllDataAsList(loadingCache);

        await().untilAsserted(() -> {
            var currentList = getAllDataAsList(loadingCache);
            System.out.println("currentList = " + currentList);
            assertThat(currentList).isNotEqualTo(initialList);
        });
                
    }

    private List<Integer> getAllDataAsList(LoadingCache<Integer, Integer> loadingCache) throws ExecutionException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < CACHE_SIZE; i++) {
            list.add(loadingCache.get(i));
        }
        return list;
    }

}