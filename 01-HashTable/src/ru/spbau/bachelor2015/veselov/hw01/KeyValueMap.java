package ru.spbau.bachelor2015.veselov.hw01;

/**
 * Common interface of HashTable and LinkedList.
 * It is used to generalize testing of those two classes.
 */
interface KeyValueMap {
    int size();
    boolean contains(String key);
    String get(String key);
    String put(String key, String value);
    String remove(String key);
    void clear();
}
