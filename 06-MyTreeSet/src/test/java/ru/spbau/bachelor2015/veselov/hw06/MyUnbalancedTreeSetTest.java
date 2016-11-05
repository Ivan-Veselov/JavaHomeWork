package ru.spbau.bachelor2015.veselov.hw06;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MyUnbalancedTreeSetTest {
    private MyUnbalancedTreeSet<Integer> setUpEmptySet() throws Exception {
        return new MyUnbalancedTreeSet<> ();
    }

    private MyUnbalancedTreeSet<Integer> setUpEmptySetWithComparator() throws Exception {
        return new MyUnbalancedTreeSet<> (Integer::compare);
    }

    private MyUnbalancedTreeSet<Integer> setUpEmptySetWithComparator(Comparator<Integer> comparator) throws Exception {
        return new MyUnbalancedTreeSet<> (comparator);
    }

    private MyTreeSet<Integer> setUpSet(@NotNull MyTreeSet<Integer> set) {
        boolean added = false;
        for (int i = 0; i != 0 || !added; i = (i + 6) % 10) {
            assertTrue(set.add(i));
            added = true;
        }

        // 0 2 4 6 8
        return set;
    }

    private void addTest1(@NotNull MyTreeSet<Integer> set) throws Exception {
        assertTrue(set.add(5));
        assertTrue(set.add(6));
        assertTrue(set.add(2));
        assertTrue(set.add(4));
        assertTrue(set.add(3));

        assertFalse(set.add(4));
        assertFalse(set.add(3));

        assertTrue(set.add(7));

        assertFalse(set.add(4));
    }

    @Test
    public void testAdd1() throws Exception {
        addTest1(setUpEmptySet());
        addTest1(setUpEmptySetWithComparator());
        addTest1((setUpEmptySetWithComparator((a, b) -> b.compareTo(a))).descendingSet());
    }

    @Test
    public void testAdd2() throws Exception {
        setUpSet(setUpEmptySet());
        setUpSet(setUpEmptySetWithComparator());
        setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet());
    }

    private void removeTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        assertTrue(set.remove(2));
        assertFalse(set.remove(2));

        assertTrue(set.add(2));
        assertTrue(set.remove(2));

        assertTrue(set.remove(6));
        assertTrue(set.remove(4));
        assertTrue(set.remove(0));
        assertTrue(set.remove(8));
    }

    @Test
    public void testRemove() throws Exception {
        removeTest(setUpSet(setUpEmptySet()));
        removeTest(setUpSet(setUpEmptySetWithComparator()));
        removeTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void sizeTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        assertEquals(5, set.size());

        assertTrue(set.add(1));
        assertEquals(6, set.size());

        assertFalse(set.add(6));
        assertEquals(6, set.size());

        assertTrue(set.remove(2));
        assertEquals(5, set.size());

        assertTrue(set.remove(4));
        assertEquals(4, set.size());

        assertTrue(set.remove(0));
        assertEquals(3, set.size());

        assertTrue(set.remove(6));
        assertEquals(2, set.size());

        assertTrue(set.remove(8));
        assertEquals(1, set.size());

        assertTrue(set.remove(1));
        assertEquals(0, set.size());

        assertTrue(set.add(0));
        assertEquals(1, set.size());
    }

    @Test
    public void testSize() throws Exception {
        sizeTest(setUpSet(setUpEmptySet()));
        sizeTest(setUpSet(setUpEmptySetWithComparator()));
        sizeTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void firstAndLastTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        assertEquals(0, set.first().intValue());
        assertEquals(8, set.last().intValue());

        assertTrue(set.remove(0));
        assertTrue(set.add(9));

        assertEquals(2, set.first().intValue());
        assertEquals(9, set.last().intValue());

        assertTrue(set.remove(2));
        assertTrue(set.remove(4));
        assertTrue(set.remove(6));
        assertTrue(set.remove(8));

        assertEquals(9, set.first().intValue());
        assertEquals(9, set.last().intValue());
    }

    @Test
    public void testFirstAndLast() throws Exception {
        firstAndLastTest(setUpSet(setUpEmptySet()));
        firstAndLastTest(setUpSet(setUpEmptySetWithComparator()));
        firstAndLastTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void floorTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        for (int i = -2; i <= 10; ++i) {
            int floor = Math.min(8, i - (Math.abs(i % 2)));
            if (floor < 0) {
                assertEquals(null, set.floor(i));
            } else {
                assertEquals(floor, set.floor(i).intValue());
            }
        }
    }

    @Test
    public void testFloor() throws Exception {
        floorTest(setUpSet(setUpEmptySet()));
        floorTest(setUpSet(setUpEmptySetWithComparator()));
        floorTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void ceilingTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        for (int i = -2; i <= 10; ++i) {
            int ceiling = Math.max(0, i + (Math.abs(i % 2)));
            if (ceiling > 8) {
                assertEquals(null, set.ceiling(i));
            } else {
                assertEquals(ceiling, set.ceiling(i).intValue());
            }
        }
    }

    @Test
    public void testCeiling() throws Exception {
        ceilingTest(setUpSet(setUpEmptySet()));
        ceilingTest(setUpSet(setUpEmptySetWithComparator()));
        ceilingTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void lowerTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        for (int i = -2; i <= 10; ++i) {
            int lower = Math.min(8, i - (Math.abs(i % 2)));
            if (lower == i) {
                lower -= 2;
            }

            if (lower < 0) {
                assertEquals(null, set.lower(i));
            } else {
                assertEquals(lower, set.lower(i).intValue());
            }
        }
    }

    @Test
    public void testLower() throws Exception {
        lowerTest(setUpSet(setUpEmptySet()));
        lowerTest(setUpSet(setUpEmptySetWithComparator()));
        lowerTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void higherTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        for (int i = -2; i <= 10; ++i) {
            int higher = Math.max(0, i + (Math.abs(i % 2)));
            if (higher == i) {
                higher += 2;
            }

            if (higher > 8) {
                assertEquals(null, set.higher(i));
            } else {
                assertEquals(higher, set.higher(i).intValue());
            }
        }
    }

    @Test
    public void testHigher() throws Exception {
        higherTest(setUpSet(setUpEmptySet()));
        higherTest(setUpSet(setUpEmptySetWithComparator()));
        higherTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void iteratorTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        assertArrayEquals(new Integer[] {0, 2, 4, 6, 8}, set.toArray());
    }

    @Test
    public void testIterator() throws Exception {
        iteratorTest(setUpSet(setUpEmptySet()));
        iteratorTest(setUpSet(setUpEmptySetWithComparator()));
        iteratorTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }

    private void descendingIteratorTest(@NotNull MyTreeSet<Integer> set) throws Exception {
        Iterator<Integer> it = set.descendingIterator();
        int value = 8;

        while (it.hasNext()) {
            assertEquals(value, it.next().intValue());
            value -= 2;
        }

        assertEquals(-2, value);
    }

    @Test
    public void testDescendingIterator() throws Exception {
        descendingIteratorTest(setUpSet(setUpEmptySet()));
        descendingIteratorTest(setUpSet(setUpEmptySetWithComparator()));
        descendingIteratorTest(setUpSet(setUpEmptySetWithComparator((a, b) -> b.compareTo(a)).descendingSet()));
    }
}
