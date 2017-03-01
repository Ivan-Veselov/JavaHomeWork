package ru.spbau.bachelor2015.veselov.hw11;

import org.jetbrains.annotations.NotNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * A JUnit Rule which checks whether all registered threads were successfully finished without any uncaught exception
 * after test execution.
 */
public class ThreadRegistrarRule implements TestRule {
    private List<Thread> threadsList;

    private boolean uncaughtExceptionFlag = false;

    /**
     * Constructs new Rule instance.
     */
    public ThreadRegistrarRule() {
        threadsList = new ArrayList<>();
    }

    /**
     * Apply method of this Rule. Constructs new Statement from a given one. New Statement additionally checks
     * registered threads.
     *
     * @param base a base Statement.
     * @param description a description of a statement (not used).
     * @return new Statement which makes additional checks.
     */
    @Override
    public Statement apply(@NotNull Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();

                for (Thread thread : threadsList) {
                    if (thread.isAlive()) {
                        throw new AliveThreadAfterEvaluationException();
                    }
                }

                if (uncaughtExceptionFlag) {
                    throw new UncaughtExceptionInThreadException();
                }
            }
        };
    }

    /**
     * Registers a thread, so that it will be checked after test execution.
     *
     * @param thread a thread to be registered.
     */
    public void registerThread(@NotNull Thread thread) {
        threadsList.add(thread);

        thread.setUncaughtExceptionHandler((t, e) -> this.uncaughtExceptionFlag = true);
    }

    /**
     * An exception which will be thrown from Statement evaluate method if an alive thread will be detected after test
     * execution.
     */
    public class AliveThreadAfterEvaluationException extends Exception {}

    /**
     * An exception which will be thrown from Statement evaluate method if there will be a thread with uncaught
     * exception after test execution.
     */
    public class UncaughtExceptionInThreadException extends Exception {}
}
