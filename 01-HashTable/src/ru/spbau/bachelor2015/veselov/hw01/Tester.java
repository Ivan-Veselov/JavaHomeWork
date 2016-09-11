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

        assert(aContainer.get("A").equals("a"));
        assert(aContainer.get("B").equals("b"));
        assert(aContainer.get("C").equals("c"));
        assert(aContainer.get("D") == null);

        assert(aContainer.remove("B").equals("b")); // !

        assert(aContainer.size() == 2);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("B"));
        assert(aContainer.contains("C"));

        assert(aContainer.get("A").equals("a"));
        assert(aContainer.get("B") == null);
        assert(aContainer.get("C").equals("c"));

        assert(aContainer.put("C", "cc").equals("c")); // !

        assert(aContainer.size() == 2);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("B"));
        assert(aContainer.contains("C"));

        assert(aContainer.get("A").equals("a"));
        assert(aContainer.get("B") == null);
        assert(aContainer.get("C").equals("cc"));

        assert(aContainer.remove("B") == null);
        assert(aContainer.size() == 2);

        assert(aContainer.remove("C").equals("cc")); // !

        assert(aContainer.size() == 1);

        assert(aContainer.contains("A"));
        assert(!aContainer.contains("C"));

        assert(aContainer.get("A").equals("a"));
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

        final int fAlphabetSize = 26;
        for (int i = 0; i < fAlphabetSize; ++i) {
            String fUpperCaseLetter = String.valueOf((char)('A' + i));
            String fLowerCaseLetter = String.valueOf((char)('a' + i));

            assert(aContainer.put(fUpperCaseLetter, fLowerCaseLetter) == null);
            assert(aContainer.size() == i + 1);
        }

        for (int i = 0; i < fAlphabetSize; ++i) {
            String fUpperCaseLetter = String.valueOf((char)('A' + i));
            String fLowerCaseLetter = String.valueOf((char)('a' + i));

            assert(aContainer.contains(fUpperCaseLetter));
            assert(!aContainer.contains(fLowerCaseLetter));

            assert(aContainer.get(fUpperCaseLetter).equals(fLowerCaseLetter));
        }
    }
}
