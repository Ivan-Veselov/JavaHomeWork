package ru.spbau.bachelor2015.veselov.hw04;

/**
 * An Exception which will be thrown in case when get method of non-instance Maybe
 * object is called.
 */
public class ValueIsNotPresentedException extends RuntimeException {
    ValueIsNotPresentedException() {
        super();
    }
}
