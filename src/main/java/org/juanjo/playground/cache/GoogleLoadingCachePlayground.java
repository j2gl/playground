package org.juanjo.playground.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * This class is a playground to test the Google Guava LoadingCache
 */
@Getter
@Slf4j
public class GoogleLoadingCachePlayground {

    public static final int CACHE_SIZE = 3;
    private final LoadingCache<Integer, Integer> loadingCache;
    private final Map<Integer, Integer> storage = new HashMap<>();
    private final Random random = new Random();

    public GoogleLoadingCachePlayground() {
        fillStorage();
        loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public Integer load(Integer key) {
                        return storage.get(key);
                    }
                });
    }

    private void fillStorage() {
        for (int i = 0; i < CACHE_SIZE; i++) {
            storage.put(i, random.nextInt(1000));
        }
    }

    private void printValuesFromCache() throws ExecutionException {
        String values = "";
        for (int i = 0; i < CACHE_SIZE; i++) {
            values = values.concat(loadingCache.get(i) + ", ");
        }
        log.info("Cache size = {}, value = {}storage={}", loadingCache.size(), values, storage);
    }

    private void slowIntensiveOperation() {
        try {
            Thread.sleep(500);
            fillStorage();
        } catch (InterruptedException e) {
            log.error("Interrupted ", e);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws ExecutionException {
        var cacheTest = new GoogleLoadingCachePlayground();
        try {
            CompletableFuture.runAsync(() -> {
                for (int i = 0; i < 100; i++) {
                    cacheTest.slowIntensiveOperation();
                }
            });

            for (int i = 0; i < 100; i++) {
                cacheTest.printValuesFromCache();
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            log.error("Interrupted ", e);
            Thread.currentThread().interrupt();
        }
    }

}
