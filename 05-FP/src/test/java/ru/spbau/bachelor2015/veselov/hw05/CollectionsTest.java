package ru.spbau.bachelor2015.veselov.hw05;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionsTest {
    private List<Integer> naturalsUpTo100 = new ArrayList<>();
    private List<String> naturalsUpTo100Str = new ArrayList<>();
    private List<Integer> evensUpTo100 = new ArrayList<>();
    private List<Integer> naturalsUpTo50 = new ArrayList<>();
    private List<String> someWords = Arrays.asList("Just", "a", "visual", "artifact");

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i <= 100; i++) {
            naturalsUpTo100.add(i);
            naturalsUpTo100Str.add(Integer.toString(i));

            if (i % 2 == 0) {
                evensUpTo100.add(i);
            }

            if (i <= 50) {
                naturalsUpTo50.add(i);
            }
        }
    }

    @Test
    public void testMap() throws Exception {
        compareIterable(Collections.map(Object::toString, naturalsUpTo100), naturalsUpTo100Str);
    }

    @Test
    public void testFilter() throws Exception {
        compareIterable(Collections.filter(n -> n % 2 == 0, naturalsUpTo100), evensUpTo100);
    }

    @Test
    public void testTakeWhile() throws Exception {
        compareIterable(Collections.takeWhile(n -> n <= 50, naturalsUpTo100), naturalsUpTo50);
    }

    @Test
    public void testTakeUnless() throws Exception {
        compareIterable(Collections.takeUnless(n -> n > 50, naturalsUpTo100), naturalsUpTo50);
    }

    @Test
    public void testFoldl() throws Exception {
        assertEquals("Java", Collections.foldl((a, s) -> a + Character.toString(s.charAt(0)), "", someWords));
    }

    @Test
    public void testFoldr() throws Exception {
        assertEquals("DjavaJ", Collections.foldr((a, s) -> a + Character.toString(s.charAt(0)), "Dj", someWords));
    }

    private <T> void compareIterable(Iterable<T> iterable1, Iterable<T> iterable2) throws Exception {
        Iterator<T> it1 = iterable1.iterator();
        Iterator<T> it2 = iterable2.iterator();

        while (it1.hasNext()) {
            assertTrue(it2.hasNext());
            assertTrue(it1.next().equals(it2.next()));
        }

        assertFalse(it2.hasNext());
    }
}