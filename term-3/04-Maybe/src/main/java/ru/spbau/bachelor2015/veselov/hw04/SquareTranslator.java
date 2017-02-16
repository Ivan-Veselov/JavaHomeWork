package ru.spbau.bachelor2015.veselov.hw04;

import java.io.*;
import java.util.function.Function;

/**
 * translate function wrapper
 */
public class SquareTranslator {
    /**
     * Reads lines of integers from input stream and writes lines of squares of these integers
     * into output stream. If line in input stream doesn't represent an integer value then
     * "null" string will be written in output stream.
     *
     * @param in input stream
     * @param out output stream
     * @throws IOException any IOException which can occur during a process of translation
     */
    public static void translate(InputStream in, OutputStream out) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                Maybe<Integer> value;
                try {
                    value = Maybe.<Integer>just(Integer.decode(line));
                } catch (NumberFormatException e) {
                    value = Maybe.<Integer>nothing();
                }

                Function<Integer, Integer> sqr = x -> x * x;
                writer.write(value.map(sqr).toString());
                writer.newLine();
            }
        }
    }
}
