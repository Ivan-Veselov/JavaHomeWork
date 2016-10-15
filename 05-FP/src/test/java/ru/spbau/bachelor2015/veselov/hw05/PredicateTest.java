package ru.spbau.bachelor2015.veselov.hw05;

import org.junit.Before;
import org.junit.Test;

public class PredicateTest {
    private Predicate<Integer> even;
    private Predicate<Integer> positive;
    private Predicate<Integer> prime;

    private static boolean isPrime(int n) {
        n = Math.abs(n);
        if (n == 0 || n == 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Before
    public void setUp() throws Exception {
        even = n -> n % 2 == 0;
        positive = n -> n > 0;

        prime = PredicateTest::isPrime;
    }

    @Test
    public void testOr() throws Exception {
        TestExtensionality.test(even.or(prime), n -> n % 2 == 0 || isPrime(n));
    }

    @Test
    public void testAnd() throws Exception {
        TestExtensionality.test(positive.and(prime), n -> n > 0 && isPrime(n));
    }

    @Test
    public void testNot() throws Exception {
        TestExtensionality.test(even.not(), n -> n % 2 != 0);
    }

    @Test
    public void testAlwaysTrue() throws Exception {
        TestExtensionality.test(Predicate.alwaysTrue(), n -> true);
    }

    @Test
    public void testAlwaysFalse() throws Exception {
        TestExtensionality.test(Predicate.alwaysFalse(), n -> false);
    }
}