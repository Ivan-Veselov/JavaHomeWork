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

    @Test
    public void testContains1() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add("aba"));
        assertTrue(trie.contains("aba"));
    }

    @Test
    public void testContains2() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add("aba"));
        assertFalse(trie.contains(""));
        assertFalse(trie.contains("a"));
        assertFalse(trie.contains("ab"));
        assertTrue(trie.contains("aba"));
        assertFalse(trie.contains("abac"));
    }

    @Test
    public void testContains3() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add("ab"));
        assertTrue(trie.add("abc"));
        assertTrue(trie.add("abd"));

        assertFalse(trie.contains("a"));
        assertTrue(trie.contains("ab"));
        assertTrue(trie.contains("abc"));
        assertTrue(trie.contains("abd"));
        assertFalse(trie.contains("abcd"));
    }

    @Test
    public void testContains4() throws Exception {
        Trie trie = new Trie();

        assertFalse(trie.contains("string"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContains5() throws Exception {
        Trie trie = new Trie();

        trie.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove1() throws Exception {
        Trie trie = new Trie();

        trie.remove(null);
    }

    @Test
    public void testRemove2() throws Exception {
        Trie trie = new Trie();

        assertFalse(trie.remove("ab"));
        assertTrue(trie.add("ab"));
        assertTrue(trie.remove("ab"));
        assertFalse(trie.contains("ab"));
    }

    @Test
    public void testRemove3() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add(""));
        assertTrue(trie.contains(""));
    }

    @Test
    public void testRemove4() throws Exception {
        Trie trie = new Trie();

        assertTrue(trie.add(""));
        assertTrue(trie.add("a"));
        assertTrue(trie.add("aa"));

        assertTrue(trie.remove("a"));
        assertTrue(trie.contains(""));
        assertFalse(trie.contains("a"));
        assertTrue(trie.contains("aa"));
        assertFalse(trie.remove("a"));
        assertFalse(trie.remove("ab"));
    }
}