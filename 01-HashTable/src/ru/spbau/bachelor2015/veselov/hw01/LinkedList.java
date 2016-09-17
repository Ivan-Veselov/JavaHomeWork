package ru.spbau.bachelor2015.veselov.hw01;

import java.lang.IllegalArgumentException;

public class LinkedList implements KeyValueMap {
    private LinkedListNode mHeadNode;
    private int mSize = 0;

    static private String sNullKeyExceptionMsg = "LinkedList key must not be null!";

    public LinkedList() {
        mHeadNode = new LinkedListNode(null);
    }

    public int size() {
        return mSize;
    }

    public boolean contains(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        return findPredecessor(aKey).next() != null;
    }

    public String get(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fNode = findPredecessor(aKey).next();
        return fNode != null ? fNode.value() : null;
    }

    public String put(String aKey, String aValue) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fPredecessor = findPredecessor(aKey);
        LinkedListNode fNode = fPredecessor.next();

        if (fNode != null) {
            return fNode.setValue(aValue);
        }

        fPredecessor.setNext(new LinkedListNode(aKey, aValue, null));
        ++mSize;

        return null;
    }

    public String remove(String aKey) {
        if (aKey == null) {
            throw new IllegalArgumentException(sNullKeyExceptionMsg);
        }

        LinkedListNode fPredecessor = findPredecessor(aKey);
        LinkedListNode fNode = fPredecessor.next();

        String fValue = null;
        if (fNode != null) {
            fValue = fNode.value();
            fPredecessor.setNext(fNode.next());
            --mSize;
        }

        return fValue;
    }

    public void clear() {
        mHeadNode.setNext(null);
        mSize = 0;
    }

    private LinkedListNode findPredecessor(String aKey) {
        LinkedListNode fNode = mHeadNode;

        while (fNode.next() != null) {
            if (fNode.next().key().equals(aKey)) {
                break;
            }

            fNode = fNode.next();
        }

        return fNode;
    }

    private class LinkedListNode {
        private KeyValuePair mKeyValuePair;

        private LinkedListNode mNext;

        public LinkedListNode(LinkedListNode aNext) {
            mKeyValuePair = new KeyValuePair();
            mNext = aNext;
        }

        public LinkedListNode(String aKey, String aValue, LinkedListNode aNext) {
            if (aKey == null) {
                throw new IllegalArgumentException("LinkedListNode key must not be null!");
            }

            mKeyValuePair = new KeyValuePair(aKey, aValue);
            mNext = aNext;
        }

        public String key() {
            return mKeyValuePair.key();
        }

        public String value() {
            return mKeyValuePair.value();
        }

        public String setValue(String aValue) {
            String fPreviousValue = mKeyValuePair.value();
            mKeyValuePair.mValue = aValue;

            return fPreviousValue;
        }

        public LinkedListNode next() {
            return mNext;
        }

        public void setNext(LinkedListNode aNext) {
            mNext = aNext;
        }
    }
}