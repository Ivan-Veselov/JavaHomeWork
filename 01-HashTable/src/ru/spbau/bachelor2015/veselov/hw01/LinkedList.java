package ru.spbau.bachelor2015.veselov.hw01;

import java.lang.IllegalArgumentException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable<KeyValuePair>, KeyValueMap {
    private LinkedListNode mHeadNode;
    private int mSize = 0;

    static private String sNullKeyExceptionMsg = "LinkedList key must not be null!";

    public LinkedList() {
        mHeadNode = new LinkedListNode();
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
        return fNode != null ? fNode.element().value() : null;
    }

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