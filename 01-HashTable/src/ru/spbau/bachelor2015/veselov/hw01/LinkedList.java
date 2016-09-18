package ru.spbau.bachelor2015.veselov.hw01;

import java.lang.IllegalArgumentException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection of String keys with String values associated with each key.
 * Implemented as linked list of pairs.
 */
public class LinkedList implements Iterable<KeyValuePair>, KeyValueMap {
    private LinkedListNode headNode;
    private int size = 0;

    private static final String NULL_KEY_EXCEPTION_MSG = "LinkedList key must not be null!";

    /**
     * Creates empty list.
     */
    public LinkedList() {
        headNode = new LinkedListNode();
    }

    /**
     * Returns size of list.
     *
     * @return number of elements currently stored in list
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether list contains element with a given key.
     * Complexity: linear in list size
     *
     * @param key a key to search for
     * @return true if list contains element with key equal to key, false otherwise
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        return findPredecessor(key).next() != null;
    }

    /**
     * Returns value associated with a given key or null if there is no
     * element with such a key in list.
     * Complexity: linear in list size
     *
     * @param key a key to search for
     * @return value associated with key or null if there is no element with key equal to key
     */
    public String get(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        LinkedListNode node = findPredecessor(key).next();
        return node != null ? node.element().value() : null;
    }

    /**
     * Associates a given value with a given key by putting such a pair in list or
     * if there is already an element with such a key then replaces previous value
     * with a new one.
     * Complexity: linear in list size
     *
     * @param key a key which will be put in list or which value will be replaced
     * @param value a value to associate with key
     * @return previous value that was associated with key or null if key was not presented in list before
     */
    public String put(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        LinkedListNode predecessor = findPredecessor(key);
        LinkedListNode node = predecessor.next();

        if (node != null) {
            return node.element().setValue(value);
        }

        predecessor.setNext(new LinkedListNode(new KeyValuePair(key, value), null));
        ++size;

        return null;
    }

    /**
     * Removes element with a given key from list.
     * Complexity: linear in list size
     *
     * @param key a key of element that will be removed
     * @return a value associated with key or null if there is no element with a key equal to key
     */
    public String remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException(NULL_KEY_EXCEPTION_MSG);
        }

        LinkedListNode predecessor = findPredecessor(key);
        LinkedListNode node = predecessor.next();

        String value = null;
        if (node != null) {
            value = node.element().value();
            predecessor.setNext(node.next());
            --size;
        }

        return value;
    }

    /**
     * Removes all elements from list.
     * Complexity: linear in list size
     */
    public void clear() {
        headNode.setNext(null);
        size = 0;
    }

    private LinkedListNode findPredecessor(String key) {
        LinkedListNode node = headNode;

        while (node.next() != null) {
            if (node.next().element().key().equals(key)) {
                break;
            }

            node = node.next();
        }

        return node;
    }

    public Iterator<KeyValuePair> iterator() {
        return new LinkedListIterator();
    }

    private static class LinkedListNode {
        private KeyValuePair keyValuePair;

        private LinkedListNode next;

        public LinkedListNode() {
            keyValuePair = null;
            next = null;
        }

        public LinkedListNode(KeyValuePair keyValuePair, LinkedListNode next) {
            this.keyValuePair = keyValuePair;
            this.next = next;
        }

        public KeyValuePair element() {
            return keyValuePair;
        }

        public LinkedListNode next() {
            return next;
        }

        public void setNext(LinkedListNode next) {
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<KeyValuePair> {
        private LinkedListNode node;

        public LinkedListIterator() {
            node = LinkedList.this.headNode.next();
        }

        public boolean hasNext() {
            return node != null;
        }

        public KeyValuePair next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            KeyValuePair value = node.element();
            node = node.next();

            return value;
        }
    }
}