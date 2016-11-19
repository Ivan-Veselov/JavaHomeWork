package ru.spbau.bachelor2015.veselov.hw09;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

public class Serialization {
    public static void serialize(@NotNull Object obj, @NotNull OutputStream stream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }

            Object fieldValue;
            try {
                field.setAccessible(true);
                fieldValue = field.get(obj);
            } catch (IllegalAccessException e) {
                /* This exception isn't supposed to be thrown, but if it is then problem is with this algorithm of
                serialization.
                 */
                throw new RuntimeException(e);
            }

            if (fieldValue instanceof String) {
                String string = (String) fieldValue;
                for (byte b : string.getBytes(StandardCharsets.UTF_8)) {
                    writer.write(Byte.toString(b));
                    writer.write(" ");
                }
            } else {
                writer.write(fieldValue.toString());
            }

            writer.newLine();
        }

        writer.flush();
    }

    public static <T> @NotNull T deserialize(@NotNull InputStream stream, @NotNull Class<T> clazz) {
        throw new UnsupportedOperationException();
    }
}
