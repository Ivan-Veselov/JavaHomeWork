package ru.spbau.bachelor2015.veselov.hw02;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void testConstructor1() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {1} });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {1, 3}, {2, 4} });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {1, 3}, {2} });
    }

    @Test
    public void testConstructor4() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {1, 3, 4}, {8, 9, 7}, {1, 2, 5} });
    }

    @Test
    public void testEquals1() throws Exception {
        Matrix<Integer> matrix1 = new Matrix<Integer>(
                new Integer[][] { {1000, 3000, 5000},
                                  {2000, 4000, 6000},
                                  {9000, 7000, 8000}
                                });

        Matrix<Integer> matrix2 = new Matrix<Integer>(
                new Integer[][] { {1000, 3000, 5000},
                                  {2000, 4000, 6000},
                                  {9000, 7000, 8000}
                                });

        assertEquals(matrix1, matrix2);
        assertEquals(matrix2, matrix1);
    }

    @Test
    public void testEquals2() throws Exception {
        Matrix<Integer> matrix1 = new Matrix<Integer>(
                new Integer[][] { {1000, 3000, 5000},
                                  {2000, 4000, 6000},
                                  {9000, 7000, 8000}
                                });

        Matrix<Integer> matrix2 = new Matrix<Integer>(
                new Integer[][] { {1000, 3000, 5000},
                                  {2000, 10000, 6000},
                                  {9000, 7000, 8000}
                                });

        assertNotEquals(matrix1, matrix2);
        assertNotEquals(matrix2, matrix1);
    }

    @Test
    public void testSort1() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {1, 3, 2}, {2, 4, 5}, {3, 6, 7} });
        matrix.sort();

        assertTrue(matrix.equals(new Matrix<Integer>(new Integer[][] { {1, 3, 2}, {2, 4, 5}, {3, 6, 7} })));
    }

    @Test
    public void testSort2() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {3, 1, 2}, {1, 3, 2}, {2, 3, 1} });
        matrix.sort();

        assertTrue(matrix.equals(new Matrix<Integer>(new Integer[][] { {1, 3, 2}, {2, 3, 1}, {3, 1, 2} })));
    }

    private boolean checkNaturalSequence(Integer[] sequence, int size) {
        if (sequence.length != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (sequence[i] != i) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testGetSpiralTraverse1() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {0} });
        assertTrue(checkNaturalSequence(matrix.getSpiralTraverse(), 1));
    }

    @Test
    public void testGetSpiralTraverse2() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(new Integer[][] { {8, 1, 2}, {7, 0, 3}, {6, 5, 4} });
        assertTrue(checkNaturalSequence(matrix.getSpiralTraverse(), 9));
    }

    @Test
    public void testGetSpiralTraverse3() throws Exception {
        Matrix<Integer> matrix = new Matrix<Integer>(
                new Integer[][] { {24, 9, 10, 11, 12},
                                  {23, 8, 1, 2, 13},
                                  {22, 7, 0, 3, 14},
                                  {21, 6, 5, 4, 15},
                                  {20, 19, 18, 17, 16}
                                });

        assertTrue(checkNaturalSequence(matrix.getSpiralTraverse(), 25));
    }
}