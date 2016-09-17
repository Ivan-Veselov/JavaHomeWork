package ru.spbau.bachelor2015.veselov.hw02;

import java.util.Arrays;
import java.util.Comparator;

public class Matrix<T extends Comparable<T>> {
    private int mSize;
    private T[][] mColumns;

    /**
     * Creates Matrix on given two-dimensional array.
     *
     * @param aColumns two-dimensional array of elements
     * @throws IllegalArgumentException if aColumns does not represent square matrix
     */
    public Matrix(T[][] aColumns) {
        mSize = aColumns.length;
        for (T[] fColumn : aColumns) {
            if (fColumn.length != mSize) {
                throw new IllegalArgumentException("Two-dimensional array must represent a square matrix!");
            }
        }

        mColumns = aColumns;
    }

    /**
     * Compares content of two matrices for equality.
     *
     * @param aMatrix a second matrix to compare with
     * @return true if matrices are equal, false otherwise
     * @throws IllegalArgumentException if aMatrix is not an instance of Matrix
     */
    public boolean equals(Object aMatrix) {
        if (!(aMatrix instanceof Matrix)) {
            throw new IllegalArgumentException();
        }

        return Arrays.deepEquals(mColumns, ((Matrix)aMatrix).mColumns);
    }

    public void sort() {
        Arrays.sort(mColumns, (T[] c1, T[] c2) -> c1[0].compareTo(c2[0]));
    }
}
