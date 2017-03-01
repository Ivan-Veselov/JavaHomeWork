package ru.spbau.bachelor2015.veselov.hw11;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.Statement;

public class ThreadRegistrarRuleTest {
    private ThreadRegistrarRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new ThreadRegistrarRule();
    }

    @Test(expected = ThreadRegistrarRule.AliveThreadAfterEvaluationException.class)
    public void testWithAliveThread() throws Throwable {
        testOnRunnable(() -> { while (true); }, false);
    }

    @Test(expected = ThreadRegistrarRule.UncaughtExceptionInThreadException.class)
    public void testWithThreadWithUncaughtException() throws Throwable {
        testOnRunnable(() -> { throw new RuntimeException(); }, true);
    }

    @Test
    public void correctTest() throws Throwable {
        testOnRunnable(() -> {}, true);
    }

    private void testOnRunnable(@NotNull Runnable runnable, boolean join) throws Throwable {
        rule.apply(new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Thread thread = new Thread(runnable);
                rule.registerThread(thread);

                thread.start();

                if (join) {
                    thread.join();
                }
            }
        }, null).evaluate();
    }
}