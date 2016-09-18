package ru.spbau.bachelor2015.veselov.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Matrix<T extends Comparable<T>> {
    private int size;
    private T[][] columns;

    /**
     * Creates Matrix on given two-dimensional array.
     *
     * @param columns two-dimensional array of elements
     * @throws IllegalArgumentException if columns does not represent square matrix of odd size
     */
    public Matrix(T[][] columns) {
        size = columns.length;
        if (size % 2 == 0) {
            throw new IllegalArgumentException("Size of matrix must be odd!");
        }

        for (T[] column : columns) {
            if (column.length != size) {
                throw new IllegalArgumentException("Two-dimensional array must represent a square matrix!");
            }
        }

        this.columns = columns;
    }

    /**
     * Compares content of two matrices for equality.
     *
     * @param matrix a second matrix to compare with
     * @return true if matrices are equal, false otherwise
     * @throws IllegalArgumentException if matrix is not an instance of Matrix
     */
    public boolean equals(Object matrix) {
        if (!(matrix instanceof Matrix)) {
            throw new IllegalArgumentException();
        }

        return Arrays.deepEquals(columns, ((Matrix)matrix).columns);
    }

    /**
     * Sorts columns of matrix. One column is less than another one
     * if first element of this column is less than first element of second
     * column.
     */
    public void sort() {
        Arrays.sort(columns, (T[] c1, T[] c2) -> c1[0].compareTo(c2[0]));
    }

    /**
     * Returns spiral traverse of matrix. Traverse begins in central cell of matrix and
     * ends in upper-left cell.
     *
     * @return an array that contains references to matrix elements in spiral traverse order
     */
    public T[] getSpiralTraverse() {
        ArrayList<T> traverse = new ArrayList<T>();

        int centerIndex = size / 2;
        traverse.add(columns[centerIndex][centerIndex]);

        for (int i = centerIndex - 1; i >= 0; i--) {
            addFrameToTraverse(traverse, i);
        }

        return traverse.toArray(columns[0]);
    }

    private void addFrameToTraverse(ArrayList<T> traverse, int frameIndex) {
        int oppositeIndex = size - frameIndex - 1;

        addLineToTraverse(traverse, new LineIterator(frameIndex, frameIndex, frameIndex, oppositeIndex));
        addLineToTraverse(traverse, new LineIterator(frameIndex, oppositeIndex, oppositeIndex, oppositeIndex));
        addLineToTraverse(traverse, new LineIterator(oppositeIndex, oppositeIndex, oppositeIndex, frameIndex));
        addLineToTraverse(traverse, new LineIterator(oppositeIndex, frameIndex, frameIndex, frameIndex));
    }

    private void addLineToTraverse(ArrayList<T> traverse, LineIterator iterator) {
        do {
            iterator.next();
            traverse.add(columns[iterator.column()][iterator.row()]);
        } while (iterator.hasNext());
    }

    private class LineIterator {
        private int currentColumn;
        private int currentRow;

        private int lastColumn;
        private int lastRow;

        private int columnAlteration;
        private int rowAlteration;

        public LineIterator(int currentColumn, int currentRow, int lastColumn, int lastRow) {
            if (currentColumn != lastColumn && currentRow != lastRow) {
                throw new IllegalArgumentException("Coordinates must represent a line!");
            }

            this.currentColumn = currentColumn;
            this.currentRow = currentRow;
            this.lastColumn = lastColumn;
            this.lastRow = lastRow;

            columnAlteration = Integer.signum(this.lastColumn - this.currentColumn);
            rowAlteration = Integer.signum(this.lastRow - this.currentRow);
        }

        public int column() {
            return currentColumn;
        }

        public int row() {
            return currentRow;
        }

        public boolean hasNext() {
            return currentColumn != lastColumn || currentRow != lastRow;
        }

        public void next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            currentColumn += columnAlteration;
            currentRow += rowAlteration;

        }
    }
}
