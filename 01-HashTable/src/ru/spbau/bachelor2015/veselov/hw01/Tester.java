package ru.spbau.bachelor2015.veselov.hw01;

public class Tester {
    public static void main(String[] args) {
        // LinkedList usage
        LinkedList fLinkedList = new LinkedList();
        assert(fLinkedList.put("A", "a") == null);
        assert(fLinkedList.put("B", "b") == null);
        assert(fLinkedList.put("C", "c") == null);

        assert(fLinkedList.contains("A"));
        assert(fLinkedList.contains("B"));
        assert(fLinkedList.contains("C"));
        assert(!fLinkedList.contains("D"));
        assert(!fLinkedList.contains("a"));
        assert(!fLinkedList.contains("b"));
        assert(!fLinkedList.contains("c"));

        assert(fLinkedList.get("A") == "a");
        assert(fLinkedList.get("B") == "b");
        assert(fLinkedList.get("C") == "c");
        assert(fLinkedList.get("D") == null);

        assert(fLinkedList.remove("B") == "b"); // !

        assert(fLinkedList.contains("A"));
        assert(!fLinkedList.contains("B"));
        assert(fLinkedList.contains("C"));

        assert(fLinkedList.get("A") == "a");
        assert(fLinkedList.get("B") == null);
        assert(fLinkedList.get("C") == "c");

        assert(fLinkedList.put("C", "cc") == "c"); // !

        assert(fLinkedList.contains("A"));
        assert(!fLinkedList.contains("B"));
        assert(fLinkedList.contains("C"));

        assert(fLinkedList.get("A") == "a");
        assert(fLinkedList.get("B") == null);
        assert(fLinkedList.get("C") == "cc");

        assert(fLinkedList.remove("B") == null);

        assert(fLinkedList.remove("C") == "cc"); // !

        assert(fLinkedList.contains("A"));
        assert(!fLinkedList.contains("C"));

        assert(fLinkedList.get("A") == "a");
        assert(fLinkedList.get("C") == null);

        fLinkedList.clear(); // !

        assert(!fLinkedList.contains("A"));
        assert(fLinkedList.get("A") == null);

        try {
            fLinkedList.put(null, null);
            assert(false);
        } catch (IllegalArgumentException e) {}

        // HashTable usage
        HashTable fHashTable = new HashTable(5);

        System.out.println(fHashTable.size());
    }
}
