package ru.spbau.bachelor2015.veselov.hw05;

/**
 * A function of one argument which either accepts it (returns true) or rejects it (returns false).
 *
 * @param <Source> Type of predicate argument.
 */
public interface Predicate<Source> extends Function1<Source, Boolean> {
    /**
     * A predicate which is always true.
     */
    Predicate<Object> ALWAYS_TRUE = argument -> true;

    /**
     * A predicate which is always false.
     */
    Predicate<Object> ALWAYS_FALSE = argument -> false;

    /**
     * Returns new predicate which is disjunction of current and given one.
     *
     * @param predicate A predicate to make a disjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Disjunction of predicates.
     */
    default <ArgSource extends Source> Predicate<ArgSource> or(Predicate<ArgSource> predicate) {
        return argument -> Predicate.this.apply(argument) || predicate.apply(argument);
    }

    /**
     * Returns new predicate which is conjunction of current and given one.
     *
     * @param predicate A predicate to make a conjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Conjunction of predicates.
     */
    default <ArgSource extends Source> Predicate<ArgSource> and(Predicate<ArgSource> predicate) {
        return argument -> Predicate.this.apply(argument) && predicate.apply(argument);
    }

    /**
     * @return A predicate which is the opposite of current one.
     */
    default Predicate<Source> not() {
        return argument -> !Predicate.this.apply(argument);
    }
}
