package ru.spbau.bachelor2015.veselov.hw11;

import org.jetbrains.annotations.NotNull;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class ThreadRegistrarRule implements TestRule {
    private List<Thread> threadsList;

    private boolean uncaughtExceptionFlag = false;

    public ThreadRegistrarRule() {
        threadsList = new ArrayList<>();
    }

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

    public void registerThread(@NotNull Thread thread) {
        threadsList.add(thread);

        thread.setUncaughtExceptionHandler((t, e) -> this.uncaughtExceptionFlag = true);
    }

    public class AliveThreadAfterEvaluationException extends Exception {}

    public class UncaughtExceptionInThreadException extends Exception {}
}
