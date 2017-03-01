package ru.spbau.bachelor2015.veselov.hw08;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.spbau.bachelor2015.veselov.hw08.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(Arrays.asList("AtestA", "BtestB", "AtestA", "BtestB"),
                     findQuotes(Arrays.asList("test/test1", "test/test2"), "test"));
    }

    @Test
    public void testPiDividedBy4() {
        // Probability < 10^(-86)
        assertTrue(Math.abs(piDividedBy4() - Math.PI / 4.0) < 0.01);
    }

    @Test
    public void testFindPrinter() {
        assertEquals(
                "B",
                findPrinter(ImmutableMap.of(
                        "A", Arrays.asList("Ababa", "acaba", "c"),
                        "B", Arrays.asList("asdasd", "afasdfsadfsaddfsadfasdfasd"),
                        "C", Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h")
                ))
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(
                ImmutableMap.of("A", 10,
                                "B", 9,
                                "C", 5),
                calculateGlobalOrder(Arrays.asList(
                    ImmutableMap.of("A", 5,
                                    "B", 3),
                    ImmutableMap.of("A", 3,
                                    "B", 6,
                                    "C", 4),
                    ImmutableMap.of("A", 2,
                                    "C", 1)
                ))
        );
    }
}
