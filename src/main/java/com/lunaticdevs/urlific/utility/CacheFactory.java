package com.lunaticdevs.urlific.utility;

import org.springframework.stereotype.Component;

/**
 * @author Saransh Kumar
 */
@Component
public class CacheFactory {

    public static <K, V> Cache<K, V> getCacheInstance(int cacheSize) {
        return new LRUCache<>(cacheSize);
    }
}
