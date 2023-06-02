package com.lunaticdevs.urlredirector.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * author: Saransh Kumar
 */
@Slf4j
public class LRUCache<K, V> implements Cache<K, V> {

    private final Deque<Node<K, V>> deque;
    private final Map<K, Node<K, V>> map;
    private final int cacheSize;

    public LRUCache(int cacheSize) {
        this.deque = new LinkedList<>();
        this.map = new HashMap<>(cacheSize);
        this.cacheSize = cacheSize;
    }

    @Override
    public V get(K key) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            deque.remove(node);
            deque.addFirst(node);
            return node.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key))
            return map.get(key).value;

        var node = new Node<>(key, value);
        if (map.size() < cacheSize) {
            deque.addFirst(node);
            map.put(key, node);
            return node.value;
        }

        var removedNode = deque.removeLast();
        deque.addFirst(node);
        map.remove(removedNode.key);
        map.put(key, node);
        return node.value;
    }

    @Override
    public void remove(K key) {
        var node = map.get(key);

        if (node != null) {
            map.remove(key);
            deque.remove(node);
        }
    }

    @Override
    public void clear() {
        map.clear();
        deque.clear();
    }

    private static class Node<K, V> {

        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Node{key=%s, value=%s}", key, value);
        }
    }
}
