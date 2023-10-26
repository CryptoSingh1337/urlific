package com.lunaticdevs.urlific.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LRUCacheTest {

    private final LRUCache<Integer, String> cache = new LRUCache<>(3);

    @Test
    void get() {
        cache.put(1, "Hello");
        cache.put(2, "World");
        cache.put(3, "Java");

        assertEquals(cache.get(1), "Hello");
        assertNull(cache.get(4));

        cache.put(4, "Python");

        assertNull(cache.get(2));
    }

    @Test
    void put() {
        cache.put(1, "Hello");
        cache.put(2, "Hello");
        cache.put(3, "Hello");
        cache.put(4, "Hello");
        cache.put(5, "Hello");
    }
}
