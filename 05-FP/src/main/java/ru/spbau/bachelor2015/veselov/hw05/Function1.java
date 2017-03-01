package ru.spbau.bachelor2015.veselov.hw05;

import org.jetbrains.annotations.NotNull;

/**
 * Interface that represents function which can be applied to one argument.
 *
 * @param <Source> Type of function argument.
 * @param <Target> Type of returning value.
 */
public interface Function1<Source, Target> {
    /**
     * Applies Function to an argument.
     *
     * @param argument Argument of a function.
     * @return The result of application.
     */
    Target apply(Source argument);

    /**
     * Composes given Function with current one.
     *
     * @param function A Function which will be composed with current.
     * @param <ArgTarget> Type of returning value of method argument.
     * @return The resulting composition.
     */
    default <ArgTarget> @NotNull Function1<Source, ArgTarget> compose(@NotNull Function1<? super Target, ArgTarget> function) {
        return argument -> function.apply(Function1.this.apply(argument));
    }
}
