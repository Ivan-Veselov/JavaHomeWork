package ru.spbau.bachelor2015.veselov.hw08;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) throws IOException {
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

    private static void unZip(@NotNull Path fileTree, @NotNull Pattern pattern) throws IOException {
        Files.walkFileTree(fileTree,
            new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try (ZipFile archive = new ZipFile(file.toFile())) {
                        unZip(archive, pattern);
                    } catch (ZipException e) {
                        // Not a zip archive
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.out.println(exc.getMessage());
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            }
        );
    }

    private static void unZip(@NotNull ZipFile archive, @NotNull Pattern pattern) {
        throw new UnsupportedOperationException();
    }
}
