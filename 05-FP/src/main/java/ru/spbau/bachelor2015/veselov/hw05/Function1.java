package ru.spbau.bachelor2015.veselov.hw05;

/**
 * Class that represents function which can be applied to one argument.
 *
 * @param <Source> Type of function argument.
 * @param <Target> Type of returning value.
 */
public abstract class Function1<Source, Target> {
    /**
     * Applies Function to an argument.
     *
     * @param argument Argument of a function.
     * @return The result of application.
     */
    public abstract Target apply(Source argument);

    /**
     * Composes given Function with current one.
     *
     * @param function A Function which will be composed with current.
     * @param <ArgTarget> Type of returning value of method argument.
     * @return The resulting composition.
     */
    public <ArgTarget> Function1<Source, ArgTarget> compose(Function1<? super Target, ArgTarget> function) {
        return new Function1<Source, ArgTarget>() {
            @Override
            public ArgTarget apply(Source argument) {
                return function.apply(Function1.this.apply(argument));
            }
        };
    }
}
