package ru.spbau.bachelor2015.veselov.hw04;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class Maybe<T> {
    private T object;

    public static <T> Maybe<T> just(T t) {
        return new Maybe<T> (t);
    }

    public static <T> Maybe<T> nothing() {
        return new Maybe<T> (null);
    }

    public @NotNull T get() {
        if (object == null) {
            throw new ValueIsNotPresentedException();
        }

        return object;
    }

    public boolean isPresent() {
        return object != null;
    }

    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        if (!isPresent()) {
            return Maybe.nothing();
        }

        return Maybe.just(mapper.apply(object));
    }

    private Maybe(T object) {
        this.object = object;
    }
}
