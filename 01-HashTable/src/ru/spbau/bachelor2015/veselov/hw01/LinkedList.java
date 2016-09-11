package ru.spbau.bachelor2015.veselov.hw01;

public class LinkedList {
    private LinkedListNode mHeadNode;

    LinkedList() {
        mHeadNode = new LinkedListNode(null, null, null);
    }

    public boolean contains(String aKey) {
        return findPredecessor(aKey).next() != null;
    }

    public String get(String aKey) {
        LinkedListNode fNode = findPredecessor(aKey).next();

        return fNode != null ? fNode.value() : null;
    }

    public String put(String aKey, String aValue) {
        // TODO

        return "";
    }

    public String remove(String aKey) {
        LinkedListNode fPredecessor = findPredecessor(aKey);
        LinkedListNode fNode = fPredecessor.next();

        String fValue = null;
        if (fNode != null) {
            fValue = fNode.value();
            fPredecessor.setNext(fNode.next());
        }

        return fValue;
    }

    public void clear() {
        mHeadNode.setNext(null);
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