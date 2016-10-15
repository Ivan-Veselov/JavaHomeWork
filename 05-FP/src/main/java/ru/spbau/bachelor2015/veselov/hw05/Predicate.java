package ru.spbau.bachelor2015.veselov.hw05;

/**
 * A function of one argument which either accepts it (returns true) or rejects it (returns false).
 *
 * @param <Source> Type of predicate argument.
 */
public abstract class Predicate<Source> extends Function1<Source, Boolean> {
    /**
     * A predicate which is always true.
     */
    public static Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        @Override
        public Boolean apply(Object argument) {
            return true;
        }
    };

    /**
     * A predicate which is always false.
     */
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
        return new Predicate<ArgSource>() {
            @Override
            public Boolean apply(ArgSource argument) {
                return Predicate.this.apply(argument) || predicate.apply(argument);
            }
        };
    }

    /**
     * Returns new predicate which is conjunction of current and given one.
     *
     * @param predicate A predicate to make a conjunction with.
     * @param <ArgSource> Type of second predicate argument.
     * @return Conjunction of predicates.
     */
    public <ArgSource extends Source> Predicate<ArgSource> and(Predicate<ArgSource> predicate) {
        return new Predicate<ArgSource>() {
            @Override
            public Boolean apply(ArgSource argument) {
                return Predicate.this.apply(argument) && predicate.apply(argument);
            }
        };
    }

    /**
     * @return A predicate which is the opposite of current one.
     */
    public Predicate<Source> not() {
        return new Predicate<Source>() {
            @Override
            public Boolean apply(Source argument) {
                return !Predicate.this.apply(argument);
            }
        };
    }
}
