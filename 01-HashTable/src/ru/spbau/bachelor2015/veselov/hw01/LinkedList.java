package ru.spbau.bachelor2015.veselov.hw01;

import java.lang.IllegalArgumentException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection of String keys with String values associated with each key.
 * Implemented as linked list of pairs.
 */
public class LinkedList implements Iterable<KeyValuePair>, KeyValueMap {
    private LinkedListNode mHeadNode;
    private int mSize = 0;

    static private String sNullKeyExceptionMsg = "LinkedList key must not be null!";

    /**
     * Creates empty list.
     */
    public LinkedList() {
        mHeadNode = new LinkedListNode();
    }

    /**
     * Returns size of list.
     *
     * @return number of elements currently stored in list
     */
    public int size() {
        return mSize;
    }

    /**
     * Checks whether list contains element with a given key.
     * Complexity: linear in list size
     *
     * @param aKey a key to search for
     * @return true if list contains element with key equal to aKey, false otherwise
     */
    public boolean contains(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return findPredecessor(aKey).next() != null;
    }

    /**
     * Returns value associated with a given key or null if there is no
     * element with such a key in list.
     * Complexity: linear in list size
     *
     * @param aKey a key to search for
     * @return value associated with aKey or null if there is no element with key equal to aKey
     */
    public String get(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fNode = findPredecessor(aKey).next();
        return fNode != null ? fNode.element().value() : null;
    }

    /**
     * Associates a given value with a given key by putting such a pair in list or
     * if there is already an element with such a key then replaces previous value
     * with a new one.
     * Complexity: linear in list size
     *
     * @param aKey a key which will be put in list or which value will be replaced
     * @param aValue a value to associate with aKey
     * @return previous value that was associated with aKey or null if aKey was not presented in list before
     */
    public String put(String aKey, String aValue) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fPredecessor = findPredecessor(aKey);
        LinkedListNode fNode = fPredecessor.next();

        if (fNode != null) {
            return fNode.element().setValue(aValue);
        }

        fPredecessor.setNext(new LinkedListNode(new KeyValuePair(aKey, aValue), null));
        ++mSize;

        return null;
    }

    /**
     * Removes element with a given key from list.
     * Complexity: linear in list size
     *
     * @param aKey a key of element that will be removed
     * @return a value associated with aKey or null if there is no element with a key equal to aKey
     */
    public String remove(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fPredecessor = findPredecessor(aKey);
        LinkedListNode fNode = fPredecessor.next();

        String fValue = null;
        if (fNode != null) {
            fValue = fNode.element().value();
            fPredecessor.setNext(fNode.next());
            --mSize;
        }

        return fValue;
    }

    /**
     * Removes all elements from list.
     * Complexity: linear in list size
     */
    public void clear() {
        mHeadNode.setNext(null);
        mSize = 0;
    }

    private LinkedListNode findPredecessor(String aKey) {
        LinkedListNode fNode = mHeadNode;

        while (fNode.next() != null) {
            if (fNode.next().element().key().equals(aKey)) {
                break;
            }

            fNode = fNode.next();
        }

        return fNode;
    }

    private class LinkedListNode {
        private KeyValuePair mKeyValuePair;

        private LinkedListNode mNext;

        public LinkedListNode() {
            mKeyValuePair = null;
            mNext = null;
        }

        public LinkedListNode(KeyValuePair aKeyValuePair, LinkedListNode aNext) {
            mKeyValuePair = aKeyValuePair;
            mNext = aNext;
        }

        public KeyValuePair element() {
            return mKeyValuePair;
        }


        public LinkedListNode next() {
            return mNext;
        }

        public void setNext(LinkedListNode aNext) {
            mNext = aNext;
        }
    }

    private class LinkedListIterator implements Iterator<KeyValuePair> {
        private LinkedListNode mNode;

        public LinkedListIterator() {
            mNode = LinkedList.this.mHeadNode.next();
        }

        public boolean hasNext() {
            return mNode != null;
        }

        public KeyValuePair next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            KeyValuePair fValue = mNode.element();
            mNode = mNode.next();

            return fValue;
        }
    }

    public Iterator<KeyValuePair> iterator() {
        return new LinkedListIterator();
    }
}