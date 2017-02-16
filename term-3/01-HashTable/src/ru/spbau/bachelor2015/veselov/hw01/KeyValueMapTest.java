package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

abstract public class KeyValueMapTest {
    private KeyValueMap map;

    abstract protected KeyValueMap createMap();

    @Before
    public void setUp() throws Exception {
        map = createMap();
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(0, map.size());
    }

    @Test
    public void testPut1() throws Exception {
        assertNull(map.put("A", "a"));
    }

    @Test
    public void testPut2() throws Exception {
        assertNull(map.put("A", "a"));
        assertNull(map.put("B", "b"));
        assertNull(map.put("C", "c"));
    }

    @Test
    public void testPut3() throws Exception {
        assertNull(map.put("A", "a"));
        assertNull(map.put("B", "b"));
        assertEquals("a", map.put("A", "c"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut4() throws Exception {
        map.put(null, "a");
    }

    @Test
    public void testPut5() throws Exception {
        assertNull(map.put("random", "1"));
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, map.size());

        assertNull(map.put("A", "a"));
        assertEquals(1, map.size());

        assertNull(map.put("B", "b"));
        assertEquals(2, map.size());

        assertEquals("b", map.put("B", "c"));
        assertEquals(2, map.size());
    }

    @Test
    public void testContains1() throws Exception {
        assertEquals(false, map.contains("A"));
        assertEquals(false, map.contains("B"));
        assertEquals(false, map.contains("C"));

        assertEquals(null, map.put("A", "a"));
        assertEquals(true, map.contains("A"));
        assertEquals(false, map.contains("B"));
        assertEquals(false, map.contains("C"));

        assertEquals(null, map.put("B", "b"));
        assertEquals(true, map.contains("A"));
        assertEquals(true, map.contains("B"));
        assertEquals(false, map.contains("C"));

        assertEquals(null, map.put("C", "c"));
        assertEquals(true, map.contains("A"));
        assertEquals(true, map.contains("B"));
        assertEquals(true, map.contains("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContains2() throws Exception {
        map.contains(null);
    }

    @Test
    public void testGet1() throws Exception {
        assertNull(map.get("A"));
        assertNull(map.get("B"));
        assertNull(map.get("C"));

        assertNull(map.put("A", "a"));
        assertEquals("a", map.get("A"));
        assertNull(map.get("B"));
        assertNull(map.get("C"));

        assertNull(map.put("B", "b"));
        assertEquals("a", map.get("A"));
        assertEquals("b", map.get("B"));
        assertNull(map.get("C"));

        assertNull(map.put("C", "c"));
        assertEquals("a", map.get("A"));
        assertEquals("b", map.get("B"));
        assertEquals("c", map.get("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet2() throws Exception {
        map.get(null);
    }

    @Test
    public void testRemove1() throws Exception {
        assertNull(map.put("A", "a"));
        assertNull(map.put("B", "b"));
        assertNull(map.put("C", "c"));

        assertEquals("a", map.remove("A"));
        assertEquals("b", map.remove("B"));
        assertNull(map.remove("A"));
        assertEquals("c", map.remove("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove2() throws Exception {
        map.remove(null);
    }

    @Test
    public void testClear() throws Exception {
        assertNull(map.put("A", "a"));
        assertNull(map.put("B", "b"));
        assertNull(map.put("C", "c"));

        map.clear();

        assertEquals(0, map.size());

        assertEquals(false, map.contains("A"));
        assertEquals(false, map.contains("B"));
        assertEquals(false, map.contains("C"));

        assertNull(map.get("A"));
        assertNull(map.get("B"));
        assertNull(map.get("C"));

        assertNull(map.remove("A"));
        assertNull(map.remove("B"));
        assertNull(map.remove("C"));
    }

    @Test
    public void testManyObjects() throws Exception {
        final int ALPHABET_SIZE = 26;
        for (int i = 0; i < ALPHABET_SIZE; ++i) {
            String upperCaseLetter = String.valueOf((char)('A' + i));
            String lowerCaseLetter = String.valueOf((char)('a' + i));

            assertNull(map.put(upperCaseLetter, lowerCaseLetter));
            assertEquals(i + 1, map.size());
        }

        for (int i = 0; i < ALPHABET_SIZE; ++i) {
            String upperCaseLetter = String.valueOf((char)('A' + i));
            String lowerCaseLetter = String.valueOf((char)('a' + i));

            assertEquals(true, map.contains(upperCaseLetter));
            assertEquals(false, map.contains(lowerCaseLetter));

            assertEquals(lowerCaseLetter, map.get(upperCaseLetter));
        }

        for (int i = 0; i < ALPHABET_SIZE; ++i) {
            String upperCaseLetter = String.valueOf((char)('A' + i));
            String lowerCaseLetter = String.valueOf((char)('a' + i));

            assertEquals(lowerCaseLetter, map.remove(upperCaseLetter));
            assertEquals(ALPHABET_SIZE - i - 1, map.size());
        }
    }
}