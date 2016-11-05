package ru.spbau.bachelor2015.veselov.hw06;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyUnbalancedTreeSetTest {
    private MyUnbalancedTreeSet<Integer> setUpEmptySet() throws Exception {
        return new MyUnbalancedTreeSet<> ();
    }

    private MyUnbalancedTreeSet<Integer> setUpEmptySetWithComparator() throws Exception {
        return new MyUnbalancedTreeSet<> (Integer::compare);
    }

    private MyUnbalancedTreeSet<Integer> setUpSet(@NotNull MyUnbalancedTreeSet<Integer> set) {
        boolean added = false;
        for (int i = 0; i != 0 || !added; i = (i + 6) % 10) {
            assertTrue(set.add(i));
            added = true;
        }

        // 0 2 4 6 8
        return set;
    }

    private void addTest1(@NotNull MyUnbalancedTreeSet<Integer> set) throws Exception {
        assertTrue(set.add(5));
        assertTrue(set.add(6));
        assertTrue(set.add(2));
        assertTrue(set.add(4));
        assertTrue(set.add(3));

        assertFalse(set.add(0));
        assertFalse(set.add(3));

        assertTrue(set.add(7));

        assertFalse(set.add(4));
    }

    @Test
    public void testAdd1() throws Exception {
        addTest1(setUpEmptySet());
        addTest1(setUpEmptySetWithComparator());
    }

    @Test
    public void testAdd2() throws Exception {
        setUpSet(setUpEmptySet());
        setUpSet(setUpEmptySetWithComparator());
    }

    /*@Test
    public void testRemove() throws Exception {
    }

    @Test
    public void testSize() throws Exception {
    }

    @Test
    public void testFirst() throws Exception {
    }

    @Test
    public void testLast() throws Exception {
    }

    @Test
    public void testFloor() throws Exception {
    }

    @Test
    public void testCeiling() throws Exception {
    }

    @Test
    public void testLower() throws Exception {
    }

    @Test
    public void testHigher() throws Exception {
    }

    @Test
    public void testIterator() throws Exception {
    }

    @Test
    public void testDescendingIterator() throws Exception {
    }

    @Test
    public void testDescendingSet() throws Exception {
    }*/
}
