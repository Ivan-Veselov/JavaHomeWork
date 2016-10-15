package ru.spbau.bachelor2015.veselov.hw05;

/**
 * Class that represents function of two arguments which can be applied to them.
 *
 * @param <Source1> Type of function first argument.
 * @param <Source2> Type of function second argument.
 * @param <Target> Type of returning value.
 */
public interface Function2<Source1, Source2, Target> {
    /**
     * Applies Function to arguments.
     *
     * @param argument1 First argument of a function.
     * @param argument2 Second argument of a function.
     * @return The result of application.
     */
    Target apply(Source1 argument1, Source2 argument2);

    /**
     * Composes given Function1 with current one.
     *
     * @param function A Function which will be composed with current.
     * @param <ArgTarget> Type of returning value of method argument.
     * @return The resulting composition.
     */
    default <ArgTarget> Function2<Source1, Source2, ArgTarget> compose(Function1<? super Target, ArgTarget> function) {
        return new Function2<Source1, Source2, ArgTarget>() {
            @Override
            public ArgTarget apply(Source1 argument1, Source2 argument2) {
                return function.apply(Function2.this.apply(argument1, argument2));
            }
        };
    }

    /**
     * Binds function first argument to a given value.
     *
     * @param argument1 A value to which first argument will be bound.
     * @return The resulting function of one argument.
     */
    default Function1<Source2, Target> bind1(Source1 argument1) {
        return new Function1<Source2, Target>() {
            @Override
            public Target apply(Source2 argument2) {
                return Function2.this.apply(argument1, argument2);
            }
        };
    }

    /**
     * Binds function second argument to a given value.
     *
     * @param argument2 A value to which second argument will be bound.
     * @return The resulting function of one argument.
     */
    default Function1<Source1, Target> bind2(Source2 argument2) {
        return new Function1<Source1, Target>() {
            @Override
            public Target apply(Source1 argument1) {
                return Function2.this.apply(argument1, argument2);
            }
        };
    }

    /**
     * Curries function.
     *
     * @return Function of one argument which returns function.
     */
    default Function1<Source1, Function1<Source2, Target>> curry() {
        return new Function1<Source1, Function1<Source2, Target>>() {
            @Override
            public Function1<Source2, Target> apply(Source1 argument) {
                return Function2.this.bind1(argument);
            }
        };
    }
}
