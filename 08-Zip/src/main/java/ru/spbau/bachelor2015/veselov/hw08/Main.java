package ru.spbau.bachelor2015.veselov.hw08;

import org.jetbrains.annotations.NotNull;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments! Must be 2.");
            return;
        }

        Path path;
        Pattern pattern;

        try {
            path = Paths.get(args[0]);
            pattern = Pattern.compile(args[1]);
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
            return;
        } catch (PatternSyntaxException e) {
            System.out.println(e.getMessage());
            return;
        }

        unZip(path, pattern);
    }

    private static void unZip(@NotNull Path fileTree, @NotNull Pattern pattern) {
        throw new UnsupportedOperationException();
    }

    private static void unZip(@NotNull ZipFile archive) {
        throw new UnsupportedOperationException();
    }
}
