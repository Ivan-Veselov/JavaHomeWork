package ru.spbau.bachelor2015.veselov.hw09;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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

    public static <T> @NotNull T deserialize(@NotNull InputStream stream, @NotNull Class<T> clazz) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Field[] fields = clazz.getDeclaredFields();

        T obj;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }

        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue;
            }

            setField(obj, field, reader.readLine());
        }

        return obj;
    }

    private static void setField(@NotNull Object obj, @NotNull Field field, @NotNull String string) throws IOException {
        field.setAccessible(true);
        Class fieldClass = field.getType();

        try {
            if (fieldClass.equals(Boolean.TYPE)) {
                field.setBoolean(obj, Boolean.parseBoolean(string));
            } else if (fieldClass.equals(Character.TYPE)) {
                field.setChar(obj, string.charAt(0));
            } else if (fieldClass.equals(Byte.TYPE)) {
                field.setByte(obj, Byte.parseByte(string));
            } else if (fieldClass.equals(Short.TYPE)) {
                field.setShort(obj, Short.parseShort(string));
            } else if (fieldClass.equals(Integer.TYPE)) {
                field.setInt(obj, Integer.parseInt(string));
            } else if (fieldClass.equals(Long.TYPE)) {
                field.set(obj, Long.parseLong(string));
            } else if (fieldClass.equals(Float.TYPE)) {
                field.setFloat(obj, Float.parseFloat(string));
            } else if (fieldClass.equals(Double.TYPE)) {
                field.setDouble(obj, Double.parseDouble(string));
            } else if (fieldClass.equals(String.class)) {
                setStringField(obj, field, string);
            } else {
                throw new IllegalArgumentException("Class must be simple.");
            }
        } catch (IllegalAccessException e) {
                /* This exception isn't supposed to be thrown, but if it is then problem is with this algorithm of
                deserialization.
                 */
            throw new RuntimeException(e);
        }
    }

    private static void setStringField(@NotNull Object obj,
                                       @NotNull Field field,
                                       @NotNull String encoded) throws IOException, IllegalAccessException {
        field.set(obj, decodeString(encoded));
    }

    private static @NotNull String decodeString(@NotNull String encoded) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(encoded));
        tokenizer.resetSyntax();
        tokenizer.wordChars('0', '9');
        tokenizer.whitespaceChars(' ', ' ');

        ArrayList<Byte> bytesArrayList = new ArrayList<>();

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            bytesArrayList.add(Byte.parseByte(tokenizer.sval));
        }

        byte[] bytesArray = new byte[bytesArrayList.size()];
        for (int i = 0; i < bytesArrayList.size(); ++i) {
            bytesArray[i] = bytesArrayList.get(i);
        }

        return new String(bytesArray, StandardCharsets.UTF_8);
    }
}
