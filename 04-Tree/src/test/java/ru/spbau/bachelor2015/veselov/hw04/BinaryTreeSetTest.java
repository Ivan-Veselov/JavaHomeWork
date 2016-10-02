package ru.spbau.bachelor2015.veselov.hw04;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryTreeSetTest {
    @Test
    public void testConstructor() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdd1() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
        set.add(null);
    }

    @Test
    public void testAdd2() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();
        assertTrue(set.add(1000));
        assertTrue(set.add(999));
        assertFalse(set.add(999));
        assertFalse(set.add(1000));
        assertTrue(set.add(1001));
        assertTrue(set.add(1002));
        assertTrue(set.add(998));
        assertFalse(set.add(1000));
    }

    @Test
    public void testContains() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();

        assertFalse(set.contains(0));

        assertTrue(set.add(0));
        assertTrue(set.contains(0));
        assertFalse(set.contains(1));

        assertFalse(set.add(0));
        assertTrue(set.contains(0));
        assertFalse(set.contains(1));

        assertTrue(set.add(1));
        assertTrue(set.contains(0));
        assertTrue(set.contains(1));

        assertTrue(set.add(-2));
        assertTrue(set.contains(0));
        assertTrue(set.contains(1));
        assertFalse(set.contains(2));
        assertFalse(set.contains(-1));
        assertTrue(set.contains(-2));
        assertFalse(set.contains(-3));
    }

    @Test
    public void testSize() throws Exception {
        BinaryTreeSet<Integer> set = new BinaryTreeSet<Integer>();

        assertEquals(0, set.size());

        assertTrue(set.add(0));
        assertEquals(1, set.size());

        assertFalse(set.add(0));
        assertEquals(1, set.size());

        assertTrue(set.add(1));
        assertEquals(2, set.size());

        assertFalse(set.add(0));
        assertEquals(2, set.size());

        assertTrue(set.add(-1));
        assertEquals(3, set.size());
    }
}