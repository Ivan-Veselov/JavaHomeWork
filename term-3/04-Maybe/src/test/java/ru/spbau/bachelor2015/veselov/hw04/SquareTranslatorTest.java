package ru.spbau.bachelor2015.veselov.hw04;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class SquareTranslatorTest {
    @Test
    public void testTranslate() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("3\naba\n11\n11a\nboom 32\n 32 bam\n32".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        SquareTranslator.translate(in, out);
        assertEquals("9\nnull\n121\nnull\nnull\nnull\n1024\n", out.toString());
    }
}