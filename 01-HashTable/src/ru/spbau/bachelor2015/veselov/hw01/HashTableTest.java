package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() throws Exception {
        HashTable hashTable = new HashTable(0);
    }

    @Test
    public void testConstructor2() throws Exception {
        HashTable hashTable = new HashTable(1);
    }

    @Test
    public void testConstructor3() throws Exception {
        HashTable hashTable = new HashTable(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor4() throws Exception {
        HashTable hashTable = new HashTable(-1);
    }
}