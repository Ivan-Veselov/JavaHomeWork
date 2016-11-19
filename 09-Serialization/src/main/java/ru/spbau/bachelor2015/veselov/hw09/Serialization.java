package ru.spbau.bachelor2015.veselov.hw09;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

public class Serialization {
    public static void serialize(@NotNull Object obj, @NotNull OutputStream stream) {
        throw new UnsupportedOperationException();
    }

    public static <T> @NotNull T deserialize(@NotNull InputStream stream, @NotNull Class<T> clazz) {
        throw new UnsupportedOperationException();
    }
}
