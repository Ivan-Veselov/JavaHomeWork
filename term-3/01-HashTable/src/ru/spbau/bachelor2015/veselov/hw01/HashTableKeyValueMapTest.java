package ru.spbau.bachelor2015.veselov.hw01;

public class HashTableKeyValueMapTest extends KeyValueMapTest {
    protected KeyValueMap createMap() {
        return new HashTable(3);
    }
}