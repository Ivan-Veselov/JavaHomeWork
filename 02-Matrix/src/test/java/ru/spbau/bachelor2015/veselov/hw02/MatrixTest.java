package ru.spbau.bachelor2015.veselov.hw02;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void testConstructor1() throws Exception {
        Matrix<Integer> fMatrix = new Matrix<Integer>(new Integer[][] { {1} });
    }

    @Test
    public void testConstructor2() throws Exception {
        Matrix<Integer> fMatrix = new Matrix<Integer>(new Integer[][] { {1, 3}, {2, 4} });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() throws Exception {
        Matrix<Integer> fMatrix = new Matrix<Integer>(new Integer[][] { {1, 3}, {2} });
    }

    @Test
    public void testEquals1() throws Exception {
        Matrix<Integer> fMatrix1 = new Matrix<Integer>(new Integer[][] { {1000, 3000}, {2000, 4000} });
        Matrix<Integer> fMatrix2 = new Matrix<Integer>(new Integer[][] { {1000, 3000}, {2000, 4000} });

        assertTrue(fMatrix1.equals(fMatrix2));
        assertTrue(fMatrix2.equals(fMatrix1));
    }

    @Test
    public void testEquals2() throws Exception {
        Matrix<Integer> fMatrix1 = new Matrix<Integer>(new Integer[][] { {1000, 3000}, {2000, 4000} });
        Matrix<Integer> fMatrix2 = new Matrix<Integer>(new Integer[][] { {2000, 4000}, {1000, 3000} });

        assertFalse(fMatrix1.equals(fMatrix2));
        assertFalse(fMatrix2.equals(fMatrix1));
    }
}