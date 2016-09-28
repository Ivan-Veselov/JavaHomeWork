package ru.spbau.bachelor2015.veselov.hw02;

import java.util.function.Function;

public class Maybe<T> {
    public static <T> Maybe<T> just(T t) {
    }

    public static <T> Maybe<T> nothing() {
    }

    public T get() {
    }

    public boolean isPresent() {
    }

    public <U> Maybe<U> map(Function<T, U> mapper) {
    }
}
