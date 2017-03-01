package ru.spbau.bachelor2015.veselov.hw05;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Function1Test {
    private Function1<Integer, Integer> id;
    private Function1<Integer, Integer> linear2;
    private Function1<Integer, Integer> linear3Shift5;
    private Function1<Integer, Integer> quadratic;

    @Before
    public void setUp() throws Exception {
        id            = x -> x;
        linear2       = x -> 2 * x;
        linear3Shift5 = x -> 3 * x + 5;
        quadratic     = x -> x * x;
    }

    @Test
    public void testCompose1() throws Exception {
        Function1<Integer, Integer> id2 = id.compose(id);
        for (int i = -100; i <= 100; i++) {
            assertEquals(new Integer(i), id2.apply(i));
        }
    }

    @Test
    public void testCompose2() throws Exception {
        Function1<Integer, Integer> id2 = linear3Shift5.compose(linear2);
        for (int i = -100; i <= 100; i++) {
            assertEquals(new Integer(2 * (3 * i + 5)), id2.apply(i));
        }
    }

    @Test
    public void testCompose3() throws Exception {
        Function1<Integer, Integer> quadratic4 = linear2.compose(quadratic);
        for (int i = -100; i <= 100; i++) {
            assertEquals(new Integer(4 * i * i), quadratic4.apply(i));
        }
    }

    @Test
    public void testTypes() throws Exception {
        Function1<Integer, String> f = Object::toString;
        Function1<String, String>  g = s -> "[" + s + "]";
        Function1<Integer, String> h = f.compose(g);

        assertEquals("[128]", h.apply(128));
    }
}