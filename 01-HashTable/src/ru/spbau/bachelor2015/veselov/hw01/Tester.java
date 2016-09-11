package ru.spbau.bachelor2015.veselov.hw01;

public class Tester {
    public static void main(String[] args) {
        test(new LinkedList());
        test(new HashTable(3));
    }

    static private void test(KeyValueMap aContainer) {
        assert(aContainer.size() == 0);

        assert(aContainer.put("A", "a") == null);
        assert(aContainer.put("B", "b") == null);
        assert(aContainer.put("C", "c") == null);

        assert(aContainer.size() == 3);

        assert(aContainer.contains("A"));
        assert(aContainer.contains("B"));
        assert(aContainer.contains("C"));
        assert(!aContainer.contains("D"));
        assert(!aContainer.contains("a"));
        assert(!aContainer.contains("b"));
        assert(!aContainer.contains("c"));

        assert(aContainer.get("A") == "a");
        assert(aContainer.get("B") == "b");
        assert(aContainer.get("C") == "c");
        assert(aContainer.get("D") == null);

        assert(aContainer.remove("B") == "b"); // !

        assert(aContainer.size() == 2);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("B"));
        assert(aContainer.contains("C"));

        assert(aContainer.get("A") == "a");
        assert(aContainer.get("B") == null);
        assert(aContainer.get("C") == "c");

        assert(aContainer.put("C", "cc") == "c"); // !

        assert(aContainer.size() == 2);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("B"));
        assert(aContainer.contains("C"));

        assert(aContainer.get("A") == "a");
        assert(aContainer.get("B") == null);
        assert(aContainer.get("C") == "cc");

        assert(aContainer.remove("B") == null);
        assert(aContainer.size() == 2);

        assert(aContainer.remove("C") == "cc"); // !

        assert(aContainer.size() == 1);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("C"));

        assert(aContainer.get("A") == "a");
        assert(aContainer.get("C") == null);

        aContainer.clear(); // !

        assert(aContainer.size() == 0);

        assert(!aContainer.contains("A"));
        assert(aContainer.get("A") == null);

        try {
            aContainer.put(null, null);
            assert(false);
        } catch (IllegalArgumentException e) {}

        assert(aContainer.size() == 0);
    }
}
