package ru.spbau.bachelor2015.veselov.hw08;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static ru.spbau.bachelor2015.veselov.hw08.SecondPartTasks.calculateGlobalOrder;
import static ru.spbau.bachelor2015.veselov.hw08.SecondPartTasks.piDividedBy4;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        fail();
    }

    @Test
    public void testPiDividedBy4() {
        // Probability < 10^(-86)
        assertTrue(Math.abs(piDividedBy4() - Math.PI / 4.0) < 0.01);
    }

    @Test
    public void testFindPrinter() {
        fail();
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
