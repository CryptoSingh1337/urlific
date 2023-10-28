package com.lunaticdevs.urlific.utility;

/**
 * @author Saransh Kumar
 */

public interface Cache<K, V> {

    V get(K key);

    V put(K key, V value);

    void remove(K key);

    void clear();
}
