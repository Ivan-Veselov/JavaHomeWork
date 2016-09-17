package ru.spbau.bachelor2015.veselov.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Matrix<T extends Comparable<T>> {
    private int mSize;
    private T[][] mColumns;

    /**
     * Creates Matrix on given two-dimensional array.
     *
     * @param aColumns two-dimensional array of elements
     * @throws IllegalArgumentException if aColumns does not represent square matrix of odd size
     */
    public Matrix(T[][] aColumns) {
        mSize = aColumns.length;
        if (mSize % 2 == 0) {
            throw new IllegalArgumentException("Size of matrix must be odd!");
        }

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

    /**
     * Sorts columns of matrix. One column is less than another one
     * if first element of this column is less than first element of second
     * column.
     */
    public void sort() {
        Arrays.sort(mColumns, (T[] c1, T[] c2) -> c1[0].compareTo(c2[0]));
    }

    /**
     * Returns spiral traverse of matrix. Traverse begins in central cell of matrix and
     * ends in upper-left cell.
     *
     * @return an array that contains references to matrix elements in spiral traverse order
     */
    public T[] getSpiralTraverse() {
        ArrayList<T> fTraverse = new ArrayList<T>();

        int fCenterIndex = mSize / 2;
        fTraverse.add(mColumns[fCenterIndex][fCenterIndex]);

        for (int i = fCenterIndex - 1; i >= 0; i--) {
            addFrameToTraverse(fTraverse, i);
        }

        return fTraverse.toArray(mColumns[0]);
    }

    private void addFrameToTraverse(ArrayList<T> aTraverse, int aFrameIndex) {
        int fOppositeIndex = mSize - aFrameIndex - 1;

        addLineToTraverse(aTraverse, new LineIterator(aFrameIndex, aFrameIndex, aFrameIndex, fOppositeIndex));
        addLineToTraverse(aTraverse, new LineIterator(aFrameIndex, fOppositeIndex, fOppositeIndex, fOppositeIndex));
        addLineToTraverse(aTraverse, new LineIterator(fOppositeIndex, fOppositeIndex, fOppositeIndex, aFrameIndex));
        addLineToTraverse(aTraverse, new LineIterator(fOppositeIndex, aFrameIndex, aFrameIndex, aFrameIndex));
    }

    private void addLineToTraverse(ArrayList<T> aTraverse, LineIterator aIterator) {
        do {
            aIterator.next();
            aTraverse.add(mColumns[aIterator.column()][aIterator.row()]);
        } while (aIterator.hasNext());
    }

    private class LineIterator {
        private int mCurrentColumn;
        private int mCurrentRow;

        private int mLastColumn;
        private int mLastRow;

        private int mColumnAlteration;
        private int mRowAlteration;

        public LineIterator(int aCurrentColumn, int aCurrentRow, int aLastColumn, int aLastRow) {
            if (aCurrentColumn != aLastColumn && aCurrentRow != aLastRow) {
                throw new IllegalArgumentException("Coordinates must represent a line!");
            }

            mCurrentColumn = aCurrentColumn;
            mCurrentRow = aCurrentRow;
            mLastColumn = aLastColumn;
            mLastRow = aLastRow;

            mColumnAlteration = Integer.signum(mLastColumn - mCurrentColumn);
            mRowAlteration = Integer.signum(mLastRow - mCurrentRow);
        }

        public int column() {
            return mCurrentColumn;
        }

        public int row() {
            return mCurrentRow;
        }

        public boolean hasNext() {
            return mCurrentColumn != mLastColumn || mCurrentRow != mLastRow;
        }

        public void next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            mCurrentColumn += mColumnAlteration;
            mCurrentRow += mRowAlteration;

        }
    }
}
