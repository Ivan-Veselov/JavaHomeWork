package ru.spbau.bachelor2015.veselov.hw04;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Class which represents an instance of an object of a given class or it's lack.
 *
 * @param <T> a class instance of which will be stored in Maybe
 */
public class Maybe<T> {
    private T object;

    /**
     * Creates Maybe object which saves inside itself a given instance.
     *
     * @param t instance to store
     * @param <T> same as class parameter
     * @return an instance of Maybe class
     */
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<T> (t);
    }

    /**
     * Creates Maybe object which represents a lack of instance.
     *
     * @param <T> same as class parameter
     * @return an instance of Maybe class
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<T> (null);
    }

    /**
     * Returns the instance which is stored or throws ValueIsNotPresentedException
     * if no instance is stored inside.
     *
     * @return instance of class T
     */
    public @NotNull T get() {
        if (object == null) {
            throw new ValueIsNotPresentedException();
        }

        return object;
    }

    /**
     * @return true if Maybe object represents an instance of class T, false otherwise
     */
    public boolean isPresent() {
        return object != null;
    }

    /**
     * Applies given function to stored instance and returns Maybe object
     * which captures the result of function execution. If Maybe doesn't
     * represent an instance a non-instance Maybe will be returned.
     *
     * @param mapper a function to apply
     * @param <U> class of value which will be returned
     * @return Maybe object which captures the result of mapper execution
     */
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
