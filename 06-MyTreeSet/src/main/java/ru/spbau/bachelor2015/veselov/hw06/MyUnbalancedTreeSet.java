package ru.spbau.bachelor2015.veselov.hw06;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Structure which maintains set of elements in order specified by natural comparison or a given comparator. To store
 * elements unbalanced tree is used, therefore worst time complexity of almost every operation is linear.
 *
 * @param <E> type of elements which will be stored.
 */
public class MyUnbalancedTreeSet<E> extends AbstractSet<E> implements MyTreeSet<E> {
    private Node<E> root = null;
    private Comparator<? super E> comparator = null;
    private int size = 0;

    /**
     * Creates tree object which will use natural ordering of elements.
     */
    public MyUnbalancedTreeSet() {
    }

    /**
     * Creates tree object which will use given comparator to order elements.
     *
     * @param comparator a comparator which will be used to order elements.
     */
    public MyUnbalancedTreeSet(@NotNull Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * @return number of elements stored in set.
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(@NotNull E e) {
        return false;
    }

    @Override
    public boolean remove(@NotNull Object o) {
        return false;
    }

    @Override
    public @NotNull E first() {
        return null;
    }

    @Override
    public @NotNull E last() {
        return null;
    }

    @Override
    public @Nullable E floor(@NotNull E e) {
        return null;
    }

    @Override
    public @Nullable E ceiling(@NotNull E e) {
        return null;
    }

    @Override
    public @Nullable E lower(@NotNull E e) {
        return null;
    }

    @Override
    public @Nullable E higher(@NotNull E e) {
        return null;
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return null;
    }

    @Override
    public @NotNull Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public @NotNull MyTreeSet<E> descendingSet() {
        return null;
    }

    private static class Node<E> {
        private E element;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(@NotNull E element) {
            this.element = element;
            this.leftChild = null;
            this.rightChild = null;
        }

        public @NotNull E getElement() {
            return element;
        }

        public @Nullable Node<E> getLeftChild() {
            return leftChild;
        }

        public @Nullable Node<E> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(@Nullable Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(@Nullable Node<E> rightChild) {
            this.rightChild = rightChild;
        }
    }
}
