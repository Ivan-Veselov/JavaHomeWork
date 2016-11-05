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

    /**
     * Adds given element to the set if it is not present.
     *
     * @param element element to add.
     * @return true if new element was added, false if it was already presented.
     */
    @Override
    public boolean add(@NotNull E element) {
        if (comparator == null) {
            comparator = (e1, e2) -> ((Comparable) e1).compareTo(e2);
        }

        if (root == null) {
            root = new Node<> (element);
            ++size;
            return true;
        }

        Node<E> leaf = findNearestLeaf(root, element);
        if (leaf == null) {
            return false;
        }

        Node<E> newNode = new Node<>(element);
        if (comparator.compare(element, leaf.getElement()) < 0) {
            leaf.setLeftChild(newNode);
        } else {
            leaf.setRightChild(newNode);
        }

        ++size;
        return true;
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

    private @Nullable Node<E> findNearestLeaf(@NotNull Node<E> node, @NotNull E element) {
        Node<E> prev = node;
        while (node != null) {
            int compareRes = comparator.compare(element, node.getElement());
            prev = node;

            if (compareRes == 0) {
                return null;
            } else if (compareRes < 0) {
                node = node.getLeftChild();
            } else if (compareRes > 0) {
                node = node.getRightChild();
            }
        }

        return prev;
    }

    private static class Node<E> {
        private E element;
        private Node<E> parent = null;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(@NotNull E element) {
            this.element = element;
        }

        public Node(@NotNull E element, @NotNull Node<E> parent) {
            this(element);
            this.parent = parent;
        }

        public @NotNull E getElement() {
            return element;
        }

        public @Nullable Node<E> getParent() {
            return parent;
        }

        public @Nullable Node<E> getLeftChild() {
            return leftChild;
        }

        public @Nullable Node<E> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(@Nullable Node<E> leftChild) {
            if (leftChild != null) {
                leftChild.cutFromTree();
            }

            this.leftChild = leftChild;
        }

        public void setRightChild(@Nullable Node<E> rightChild) {
            if (rightChild != null) {
                rightChild.cutFromTree();
            }

            this.rightChild = rightChild;
        }

        public @NotNull Node<E> cutFromTree() {
            if (parent == null) {
                return this;
            }

            if (parent.getLeftChild() == this) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }

            parent = null;
            return this;
        }
    }
}
