package ru.spbau.bachelor2015.veselov.hw08;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
        fail();
    }
}
