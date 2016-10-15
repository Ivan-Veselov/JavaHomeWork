package ru.spbau.bachelor2015.veselov.hw05;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class which provides functions which use Collections and Functional classes together.
 */
public final class Collections {
    /**
     * Applies given function to all elements of iterable object and returns iterable object of results of application.
     *
     * @param function A function which is applied to elements.
     * @param iterable An iterable object which represents a list of elements.
     * @param <Source> Type of function argument.
     * @param <Target> Type of function returning value.
     * @param <T> Type of elements in iterable object.
     * @return Non modifiable Iterable object which consists of results of application.
     */
    public static <Source, Target, T extends Source> @NotNull Iterable<Target> map(
            @NotNull Function1<Source, Target> function, @NotNull Iterable<T> iterable) {
        return () -> new Iterator<Target>() {
            private Iterator<T> iterator = iterable.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Target next() {
                return function.apply(iterator.next());
            }
        };
    }

    /**
     * Filters elements of iterable object by using a given predicate.
     *
     * @param predicate A predicate to filter elements.
     * @param iterable A list of elements.
     * @param <Source> Type of predicate argument.
     * @param <T> Type of elements in a list.
     * @return New non modifiable list of elements which were accepted by predicate.
     */
    public static <Source, T extends Source> @NotNull Iterable<T> filter(@NotNull Predicate<Source> predicate,
                                                                         @NotNull Iterable<T> iterable) {
        return () -> new Iterator<T>() {
            private Iterator<T> iterator = iterable.iterator();
            private T element = null;

            @Override
            public boolean hasNext() {
                if (element != null) {
                    return true;
                }

                while (iterator.hasNext()) {
                    element = iterator.next();
                    if (predicate.apply(element)) {
                        return true;
                    }
                }

                element = null;
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T tmp = element;
                element = null;
                return tmp;
            }
        };
    }

    /**
     * Returns some elements from the beginning of a given list of elements up to an element which was rejected by a
     * given predicate. Rejected element will not be included in resulting list.
     *
     * @param predicate A predicate which determines terminal element.
     * @param iterable A list of elements.
     * @param <Source> Type of predicate argument.
     * @param <T> Type of elements in a list.
     * @return New non modifiable list which is a prefix of a given one.
     */
    public static <Source, T extends Source> @NotNull Iterable<T> takeWhile(@NotNull Predicate<Source> predicate,
                                                                            @NotNull Iterable<T> iterable) {
        return () -> new Iterator<T>() {
            Iterator<T> iterator = iterable.iterator();
            T element = null;
            boolean terminated = false;

            @Override
            public boolean hasNext() {
                if (terminated) {
                    return false;
                }

                if (element != null) {
                    return true;
                }

                if (!iterator.hasNext()) {
                    return false;
                }

                element = iterator.next();
                if (predicate.apply(element)) {
                    return true;
                }

                terminated = true;
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T tmp = element;
                element = null;
                return tmp;
            }
        };
    }

    /**
     * Returns some elements from the beginning of a given list of elements up to an element which was accepted by a
     * given predicate. Accepted element will not be included in resulting list.
     *
     * @param predicate A predicate which determines terminal element.
     * @param iterable A list of elements.
     * @param <Source> Type of predicate argument.
     * @param <T> Type of elements in a list.
     * @return New non modifiable list which is a prefix of a given one.
     */
    public static <Source, T extends Source> @NotNull Iterable<T> takeUnless(@NotNull Predicate<Source> predicate,
                                                                             @NotNull Iterable<T> iterable) {
        return takeWhile(predicate.not(), iterable);
    }

    /**
     * Folds given list of elements into one value which is computed by a given function of two arguments. Method
     * sequentially (from the beginning of the list) applies this function to the elements of the list and accumulates
     * result. The function should accept accumulated result and next element.
     *
     * @param function A function of two arguments which folds a list of elements.
     * @param initialValue Initially accumulated result.
     * @param iterable A list of elements.
     * @param <Source1> Type of first argument of a function.
     * @param <Source2> Type of second argument of a function.
     * @param <Target> Type of returning value of a function.
     * @param <T> Type of elements in a list.
     * @return One value - the result of folding.
     */
    public static <Source1, Source2, Target extends Source1, T extends Source2> @NotNull Target foldl(
            @NotNull Function2<Source1, Source2, Target> function,
            Source1 initialValue,
            @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    /**
     * Folds given list of elements into one value which is computed by a given function of two arguments. Method
     * sequentially (from the end of the list) applies this function to the elements of the list and accumulates
     * result. The function should accept accumulated result and next element.
     *
     * @param function A function of two arguments which folds a list of elements.
     * @param initialValue Initially accumulated result.
     * @param iterable A list of elements.
     * @param <Source1> Type of first argument of a function.
     * @param <Source2> Type of second argument of a function.
     * @param <Target> Type of returning value of a function.
     * @param <T> Type of elements in a list.
     * @return One value - the result of folding.
     */
    public static <Source1, Source2, Target extends Source1, T extends Source2> @NotNull Target foldr(
            @NotNull Function2<Source1, Source2, Target> function,
            Source1 initialValue,
            @NotNull Iterable<T> iterable) {
        throw new UnsupportedOperationException();
    }

    private Collections() {
        throw new UnsupportedOperationException();
    }
}
