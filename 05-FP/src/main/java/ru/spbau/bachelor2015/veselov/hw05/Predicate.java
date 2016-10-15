package ru.spbau.bachelor2015.veselov.hw05;

/**
 * A function of one argument which either accepts it (returns true) or rejects it (returns false).
 *
 * @param <Source> Type of predicate argument.
 */
public abstract class Predicate<Source> extends Function1<Source, Boolean> {
    public static Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        @Override
        public Boolean apply(Object argument) {
            return true;
        }
    };

    public static Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() {
        @Override
        public Boolean apply(Object argument) {
            return false;
        }
    };

    /**
     * Returns new predicate which is disjunction of current and given one.
     *
     * @param predicate A predicate to make a disjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Disjunction of predicates.
     */
    public <ArgSource extends Source> Predicate<ArgSource> or(Predicate<ArgSource> predicate) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns new predicate which is conjunction of current and given one.
     *
     * @param predicate A predicate to make a conjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Conjunction of predicates.
     */
    public <ArgSource extends Source> Predicate<ArgSource> and(Predicate<ArgSource> predicate) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return A predicate which is the opposite of current one.
     */
    public Predicate<Source> not() {
        throw new UnsupportedOperationException();
    }
}
