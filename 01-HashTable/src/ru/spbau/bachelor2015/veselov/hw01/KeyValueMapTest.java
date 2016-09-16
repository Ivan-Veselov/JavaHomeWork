package ru.spbau.bachelor2015.veselov.hw01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

abstract public class KeyValueMapTest {
    private KeyValueMap fMap;

    abstract protected KeyValueMap createMap();

    @Before
    public void setUp() throws Exception {
        fMap = createMap();
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(0, fMap.size());
    }

    @Test
    public void testPut1() throws Exception {
        assertEquals(null, fMap.put("A", "a"));
    }

    @Test
    public void testPut2() throws Exception {
        assertEquals(null, fMap.put("A", "a"));
        assertEquals(null, fMap.put("B", "b"));
        assertEquals(null, fMap.put("C", "c"));
    }

    @Test
    public void testPut3() throws Exception {
        assertEquals(null, fMap.put("A", "a"));
        assertEquals(null, fMap.put("B", "b"));
        assertEquals("a", fMap.put("A", "c"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut4() throws Exception {
        fMap.put(null, "a");
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, fMap.size());

        assertEquals(null, fMap.put("A", "a"));
        assertEquals(1, fMap.size());

        assertEquals(null, fMap.put("B", "b"));
        assertEquals(2, fMap.size());

        assertEquals("b", fMap.put("B", "c"));
        assertEquals(2, fMap.size());
    }

    @Test
    public void testContains1() throws Exception {
        assertEquals(false, fMap.contains("A"));
        assertEquals(false, fMap.contains("B"));
        assertEquals(false, fMap.contains("C"));

        assertEquals(null, fMap.put("A", "a"));
        assertEquals(true, fMap.contains("A"));
        assertEquals(false, fMap.contains("B"));
        assertEquals(false, fMap.contains("C"));

        assertEquals(null, fMap.put("B", "b"));
        assertEquals(true, fMap.contains("A"));
        assertEquals(true, fMap.contains("B"));
        assertEquals(false, fMap.contains("C"));

        assertEquals(null, fMap.put("C", "c"));
        assertEquals(true, fMap.contains("A"));
        assertEquals(true, fMap.contains("B"));
        assertEquals(true, fMap.contains("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContains2() throws Exception {
        fMap.contains(null);
    }

    @Test
    public void testGet1() throws Exception {
        assertEquals(null, fMap.get("A"));
        assertEquals(null, fMap.get("B"));
        assertEquals(null, fMap.get("C"));

        assertEquals(null, fMap.put("A", "a"));
        assertEquals("a", fMap.get("A"));
        assertEquals(null, fMap.get("B"));
        assertEquals(null, fMap.get("C"));

        assertEquals(null, fMap.put("B", "b"));
        assertEquals("a", fMap.get("A"));
        assertEquals("b", fMap.get("B"));
        assertEquals(null, fMap.get("C"));

        assertEquals(null, fMap.put("C", "c"));
        assertEquals("a", fMap.get("A"));
        assertEquals("b", fMap.get("B"));
        assertEquals("c", fMap.get("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGet2() throws Exception {
        fMap.get(null);
    }

    @Test
    public void testRemove1() throws Exception {
        assertEquals(null, fMap.put("A", "a"));
        assertEquals(null, fMap.put("B", "b"));
        assertEquals(null, fMap.put("C", "c"));

        assertEquals("a", fMap.remove("A"));
        assertEquals("b", fMap.remove("B"));
        assertEquals(null, fMap.remove("A"));
        assertEquals("c", fMap.remove("C"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemove2() throws Exception {
        fMap.remove(null);
    }

    @Test
    public void clear() throws Exception {
        assertEquals(null, fMap.put("A", "a"));
        assertEquals(null, fMap.put("B", "b"));
        assertEquals(null, fMap.put("C", "c"));

        fMap.clear();

        assertEquals(0, fMap.size());

        assertEquals(false, fMap.contains("A"));
        assertEquals(false, fMap.contains("B"));
        assertEquals(false, fMap.contains("C"));

        assertEquals(null, fMap.get("A"));
        assertEquals(null, fMap.get("B"));
        assertEquals(null, fMap.get("C"));

        assertEquals(null, fMap.remove("A"));
        assertEquals(null, fMap.remove("B"));
        assertEquals(null, fMap.remove("C"));
    }
}