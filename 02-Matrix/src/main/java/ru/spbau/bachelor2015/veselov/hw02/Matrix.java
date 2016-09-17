package ru.spbau.bachelor2015.veselov.hw02;

import java.util.Arrays;

public class Matrix<T> {
    private int mSize;
    private T[][] mColumns;

    /**
     * Creates Matrix on given two-dimensional array.
     *
     * @param aColumns two-dimensional array of elements
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
     */
    public boolean equals(Object aMatrix) {
        return Arrays.deepEquals(mColumns, ((Matrix)aMatrix).mColumns);
    }
}
