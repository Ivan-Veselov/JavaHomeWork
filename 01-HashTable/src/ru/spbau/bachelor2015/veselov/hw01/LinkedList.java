package ru.spbau.bachelor2015.veselov.hw01;

import java.lang.IllegalArgumentException;

public class LinkedList implements KeyValueMap {
    private LinkedListNode mHeadNode;
    private int mSize = 0;

    public LinkedList() {
        mHeadNode = new LinkedListNode(null);
    }

    public int size() {
        return mSize;
    }

    public boolean contains(String aKey) {
        return findPredecessor(aKey).next() != null;
    }

    public String get(String aKey) {
        LinkedListNode fNode = findPredecessor(aKey).next();

        return fNode != null ? fNode.value() : null;
    }

    public String put(String aKey, String aValue) {
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
}

class LinkedListNode {
    private String mKey = null;
    private String mValue = null;

    private LinkedListNode mNext;

    public LinkedListNode(LinkedListNode aNext) {
        mNext = aNext;
    }

    public LinkedListNode(String aKey, String aValue, LinkedListNode aNext) {
        if (aKey == null) {
            throw new IllegalArgumentException("LinkedListNode key must not be null!");
        }

        mKey = aKey;
        mValue = aValue;
        mNext = aNext;
    }

    public String key() {
        return mKey;
    }

    public String value() {
        return mValue;
    }

    public String setValue(String aValue) {
        String fPreviousValue = mValue;
        mValue = aValue;

        return fPreviousValue;
    }

    public LinkedListNode next() {
        return mNext;
    }

    public void setNext(LinkedListNode aNext) {
        mNext = aNext;
    }
}