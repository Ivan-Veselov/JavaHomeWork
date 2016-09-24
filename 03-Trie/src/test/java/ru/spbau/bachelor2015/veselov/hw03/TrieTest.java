package ru.spbau.bachelor2015.veselov.hw03;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testConstructor() throws Exception {
        Trie trie = new Trie();
    }

    @Test
    public void testAdd1() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add(""));
        assertTrue(trie.add("a"));
        assertTrue(trie.add("b"));
        assertTrue(trie.add("cabaabac"));


        assertFalse(trie.add("cabaabac"));
        assertFalse(trie.add("a"));
        assertFalse(trie.add("b"));
        assertFalse(trie.add(""));
    }

    @Test
    public void testAdd2() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add("abacaba"));
        assertFalse(trie.add("abacaba"));
    }

    @Test
    public void testAdd3() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add(""));
        assertTrue(trie.add("s"));
        assertTrue(trie.add("st"));
        assertTrue(trie.add("str"));
        assertTrue(trie.add("stri"));
        assertTrue(trie.add("strin"));
        assertTrue(trie.add("string"));

        assertFalse(trie.add("string"));
        assertFalse(trie.add("strin"));
        assertFalse(trie.add("stri"));
        assertFalse(trie.add("str"));
        assertFalse(trie.add("st"));
        assertFalse(trie.add("s"));
        assertFalse(trie.add(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd4() throws Exception {
        Trie trie = new Trie();

        trie.add(null);
    }
}