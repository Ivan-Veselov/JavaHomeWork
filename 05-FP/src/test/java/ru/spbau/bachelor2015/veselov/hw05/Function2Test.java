package ru.spbau.bachelor2015.veselov.hw05;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Function2Test {
    Function1<Integer, Integer> linear2;
    Function1<Integer, Integer> quadratic;

    Function2<Integer, Integer, Integer> linearXY;
    Function2<Integer, Integer, Integer> cubicXXY;

    @Before
    public void setUp() throws Exception {
        linear2   = x -> 2 * x;
        quadratic = x -> x * x;

        linearXY    = (x, y) -> x + y;
        cubicXXY = (x, y) -> x * x * y;
    }

    @Test
    public void testCompose1() throws Exception {
        Function2<Integer, Integer, Integer> quadraticSum = linearXY.compose(quadratic);

        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
                assertEquals(new Integer((i + j) * (i + j)), quadraticSum.apply(i, j));
            }
        }
    }

    @Test
    public void testCompose2() throws Exception {
        Function2<Integer, Integer, Integer> cubic2XXY = cubicXXY.compose(linear2);

        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
                assertEquals(new Integer(2 * i * i * j), cubic2XXY.apply(i, j));
            }
        }
    }

    @Test
    public void testBind1() throws Exception {
        TestExtensionality.test(linearXY.bind1(3), y -> 3 + y);
        TestExtensionality.test(cubicXXY.bind1(3), y -> 3 * 3 * y);
    }

    @Test
    public void testBind2() throws Exception {
        TestExtensionality.test(linearXY.bind2(9), x -> x + 9);
        TestExtensionality.test(cubicXXY.bind2(3), x -> x * x * 3);
    }

    @Test
    public void testCurry() throws Exception {
        TestExtensionality.test(linearXY.curry().apply(-5), y -> -5 + y);
        TestExtensionality.test(cubicXXY.curry().apply(-5), y -> -5 * (-5) * y);
    }

    @Test
    public void testTypes() throws Exception {
        Function2<Integer, String, String> f = (n, s) -> s + n.toString() + s;
        Function1<Integer, String> g = f.bind2("|");

        assertEquals("|128|", g.apply(128));
    }
}

class TestExtensionality {
    public static <T> void test(Function1<Integer, T> f1, Function1<Integer, T> f2) {
        final int limit = 100;
        for (int i = -limit; i <= limit; i++) {
            assertTrue(f1.apply(i).equals(f2.apply(i)));
        }
    }
}