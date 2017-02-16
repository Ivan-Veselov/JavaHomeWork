package ru.spbau.bachelor2015.veselov.hw04;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class MaybeTest {
    @Test
    public void testJust() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>just(new Integer(1024));
    }

    @Test
    public void testNothing() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>nothing();
    }

    @Test
    public void testIsPresent1() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>just(new Integer(1024));
        assertTrue(maybe.isPresent());
    }

    @Test
    public void testIsPresent2() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>nothing();
        assertFalse(maybe.isPresent());
    }

    @Test(expected = ValueIsNotPresentedException.class)
    public void testGet1() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>nothing();
        Integer i = maybe.get();
    }

    @Test
    public void testGet2() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>just(new Integer(1024));
        assertEquals(1024, maybe.get().intValue());
    }

    @Test
    public void testMap1() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>just(new Integer(1024));
        Function<Integer, Integer> f = x -> x * 2;
        assertEquals(2048, maybe.map(f).get().intValue());
    }

    @Test
    public void testMap2() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>nothing();
        Function<Integer, Integer> f = x -> x * 2;
        assertFalse(maybe.map(f).isPresent());
    }

    @Test
    public void testToString1() throws Exception {
        Maybe<Integer> maybe = Maybe.<Integer>nothing();
        assertEquals("null", maybe.toString());
    }

    @Test
    public void testToString2() throws Exception {
        Integer i = new Integer(1024);
        Maybe<Integer> maybe = Maybe.<Integer>just(i);
        assertEquals(i.toString(), maybe.toString());
    }
}