package ru.spbau.bachelor2015.veselov.hw08;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    private Path initial = Paths.get("test-initial");
    private Path result = Paths.get("test-result");
    private Path test = Paths.get("test");

    private Path removeFirstNames(Path initPath, int amount) {
        if (initPath.getNameCount() <= amount) {
            return Paths.get("");
        } else {
            return initPath.subpath(amount, initPath.getNameCount());
        }
    }

    @Before
    public void setUp() throws Exception {
        if (Files.exists(test)) {
            FileUtils.deleteDirectory(test.toFile());
        }

        FileUtils.copyDirectory(initial.toFile(), test.toFile());
    }

    private HashSet<Path> getHashSetRepresentation(Path structure) throws Exception {
        HashSet<Path> set = new HashSet<>();

        Files.walkFileTree(structure,
            new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    set.add(removeFirstNames(dir, structure.getNameCount()));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    set.add(removeFirstNames(file, structure.getNameCount()));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    throw exc;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            }
        );

        return set;
    }

    private void assertEqualStructures(Path structure1, Path structure2) throws Exception {
        HashSet<Path> set1 = getHashSetRepresentation(structure1);
        HashSet<Path> set2 = getHashSetRepresentation(structure2);

        assertEquals(set1, set2);

        for (Path path : set1) {
            File file1 = structure1.resolve(path).toFile();
            File file2 = structure2.resolve(path).toFile();

            if (file1.isDirectory()) {
                assertTrue(file2.isDirectory());
                continue;
            }

            assertTrue(FileUtils.contentEquals(file1, file2));
        }
    }

    @Test
    public void main() throws Exception {
        Main.main(new String[] {test.toString(), ".*test(0|1)"});
        assertEqualStructures(test, result);
    }
}