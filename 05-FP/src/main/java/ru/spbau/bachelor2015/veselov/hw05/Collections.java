package ru.spbau.bachelor2015.veselov.hw05;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Class which provides functions which use Collections and Functional classes together.
 */
public class Collections {
    /**
     * Applies given function to all elements of iterable object and returns iterable object of results of application.
     *
     * @param function A function which is applied to elements.
     * @param iterable An iterable object which represents a list of elements.
     * @param <Source> Type of function argument.
     * @param <Target> Type of function returning value.
     * @param <T> Type of elements in iterable object.
     * @return Iterable object which consists of results of application.
     */
    public static <Source, Target, T extends Source> @NotNull Iterable<Target> map(
            @NotNull Function1<Source, Target> function, @NotNull Iterable<T> iterable) {
        return new Iterable<Target>() {
            @Override
            public Iterator<Target> iterator() {
                return new IteratorWrapper();
            }

            class IteratorWrapper implements Iterator<Target> {
                private Iterator<T> iterator = iterable.iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public Target next() {
                    return function.apply(iterator.next());
                }
            }
        };
    }

    public static <Source, T extends Source> @NotNull Iterable<T> filter(@NotNull Predicate<Source> predicate,
                                                                         @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <Source, T extends Source> @NotNull Iterable<T> takeWhile(@NotNull Predicate<Source> predicate,
                                                                            @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <Source, T extends Source> @NotNull Iterable<T> takeUnless(@NotNull Predicate<Source> predicate,
                                                                             @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <Source1, Source2, Target extends Source1, T extends Source2> @NotNull Target foldr(
            @NotNull Function2<Source1, Source2, Target> function,
            Source1 initialValue,
            @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    public static <Source1, Source2, Target extends Source1, T extends Source2> @NotNull Target foldl(
            @NotNull Function2<Source1, Source2, Target> function,
            Source1 initialValue,
            @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    private Collections() {
        throw new UnsupportedOperationException();
    }
}
