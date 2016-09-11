package ru.spbau.bachelor2015.veselov.hw01;

interface KeyValueMap {
    int size();
    boolean contains(String aKey);
    String get(String aKey);
    String put(String aKey, String aValue);
    String remove(String aKey);
    void clear();
}
