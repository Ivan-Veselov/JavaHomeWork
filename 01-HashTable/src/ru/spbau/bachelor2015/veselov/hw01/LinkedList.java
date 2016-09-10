package ru.spbau.bachelor2015.veselov.hw01;

public class LinkedList {
    public boolean contains(String aKey) {
        // TODO

        return false;
    }

    public String get(String aKey) {
        // TODO

        return "";
    }

    public String put(String aKey, String aValue) {
        // TODO

        return "";
    }

    public String remove(String aKey) {
        // TODO

        return "";
    }

    public void clear() {
        // TODO
    }
}

class LinkedListNode {
    private String mKey;
    private String mValue;

    private LinkedListNode mNext;

    LinkedListNode(String aKey, String aValue, LinkedListNode aNext) {
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

    public LinkedListNode next() {
        return mNext;
    }

    public void setNext(LinkedListNode aNext) {
        mNext = aNext;
    }
}