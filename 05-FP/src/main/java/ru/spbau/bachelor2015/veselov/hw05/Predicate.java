package ru.spbau.bachelor2015.veselov.hw05;

import org.jetbrains.annotations.NotNull;

/**
 * A function of one argument which either accepts it (returns true) or rejects it (returns false).
 *
 * @param <Source> Type of predicate argument.
 */
public interface Predicate<Source> extends Function1<Source, Boolean> {
    /**
     * Returns new predicate which is disjunction of current and given one.
     *
     * @param predicate A predicate to make a disjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Disjunction of predicates.
     */
    default <ArgSource extends Source> @NotNull Predicate<ArgSource> or(@NotNull Predicate<ArgSource> predicate) {
        return argument -> Predicate.this.apply(argument) || predicate.apply(argument);
    }

    /**
     * Returns new predicate which is conjunction of current and given one.
     *
     * @param predicate A predicate to make a conjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Conjunction of predicates.
     */
    default <ArgSource extends Source> @NotNull Predicate<ArgSource> and(@NotNull Predicate<ArgSource> predicate) {
        return argument -> Predicate.this.apply(argument) && predicate.apply(argument);
    }

    /**
     * @return A predicate which is the opposite of current one.
     */
    default @NotNull Predicate<Source> not() {
        return argument -> !Predicate.this.apply(argument);
    }

    /**
     * @return A predicate which is always true.
     */
    static <Source> @NotNull Predicate<Source> alwaysTrue() {
        return argument -> true;
    }

    /**
     * @return A predicate which is always false.
     */
    static <Source> @NotNull Predicate<Source> alwaysFalse() {
        return argument -> false;
    }
}
