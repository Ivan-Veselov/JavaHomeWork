package ru.spbau.bachelor2015.veselov.hw04;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
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
    public void contains() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}