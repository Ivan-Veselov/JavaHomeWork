package ru.spbau.bachelor2015.veselov.hw06;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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

        Node<E> leaf = findElementOrLeaf(root, element);
        int compareRes = comparator.compare(element, leaf.getElement());

        if (compareRes == 0) {
            return false;
        }

        Node<E> newNode = new Node<>(element);
        if (compareRes < 0) {
            leaf.setLeftChild(newNode);
        } else {
            leaf.setRightChild(newNode);
        }

        ++size;
        return true;
    }

    /**
     * Removes given element from tree.
     *
     * @param obj an element to remove.
     * @return true if element was removed, false if it was not presented in tree.
     */
    @Override
    public boolean remove(@NotNull Object obj) {
        E element = (E)obj;

        if (root == null) {
            return false;
        }

        Node<E> node = findElementOrLeaf(root, element);
        if (comparator.compare(element, node.getElement()) != 0) {
            return false;
        }

        Node<E> parent = node.getParent();
        Node<E> leftChild = node.getLeftChild();
        Node<E> rightChild = node.getRightChild();

        if (leftChild != null && rightChild != null) {
            Node<E> rightSubTreeLeftmostLeaf = findElementOrLeaf(rightChild, leftChild.getElement());
            rightSubTreeLeftmostLeaf.setLeftChild(leftChild);
            leftChild = null;
        }

        if (leftChild == null && rightChild == null) {
            node.cutFromTree();
            if (parent == null) {
                root = null;
            }
        } else if (leftChild != null) {
            node.replaceWith(leftChild);
            if (parent == null) {
                root = leftChild;
            }
        } else {
            node.replaceWith(rightChild);
            if (parent == null) {
                root = rightChild;
            }
        }

        --size;
        return true;
    }

    /**
     * Returns the lowest element in tree.
     */
    @Override
    public @NotNull E first() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return root.leftmost().getElement();
    }

    /**
     * Returns the greatest element in tree.
     */
    @Override
    public @NotNull E last() {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return root.rightmost().getElement();
    }

    /**
     * Returns the greatest element in tree which is less than or equal to a given one. If there is no such element then
     * null pointer will be returned.
     *
     * @param element a given element.
     */
    @Override
    public @Nullable E floor(@NotNull E element) {
        if (root == null) {
            return null;
        }

        Node<E> node = findElementOrLeaf(root, element);
        if (comparator.compare(element, node.getElement()) >= 0) {
            return node.getElement();
        }

        node = node.previous();
        if (node == null) {
            return null;
        }

        return node.getElement();
    }

    /**
     * Returns the least element in tree which is greater than or equal to a given one. If there is no such element then
     * null pointer will be returned.
     *
     * @param element a given element.
     */
    @Override
    public @Nullable E ceiling(@NotNull E element) {
        if (root == null) {
            return null;
        }

        Node<E> node = findElementOrLeaf(root, element);
        if (comparator.compare(element, node.getElement()) <= 0) {
            return node.getElement();
        }

        node = node.next();
        if (node == null) {
            return null;
        }

        return node.getElement();
    }

    /**
     * Returns the greatest element in tree which is less than a given one. If there is no such element then
     * null pointer will be returned.
     *
     * @param element a given element.
     */
    @Override
    public @Nullable E lower(@NotNull E element) {
        if (root == null) {
            return null;
        }

        Node<E> node = findElementOrLeaf(root, element);
        if (comparator.compare(element, node.getElement()) > 0) {
            return node.getElement();
        }

        node = node.previous();
        if (node == null) {
            return null;
        }

        return node.getElement();
    }

    /**
     * Returns the least element in tree which is greater than a given one. If there is no such element then
     * null pointer will be returned.
     *
     * @param element a given element.
     */
    @Override
    public @Nullable E higher(@NotNull E element) {
        if (root == null) {
            return null;
        }

        Node<E> node = findElementOrLeaf(root, element);
        if (comparator.compare(element, node.getElement()) < 0) {
            return node.getElement();
        }

        node = node.next();
        if (node == null) {
            return null;
        }

        return node.getElement();
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node;

            {
                node = root == null ? null : root.leftmost();
            }

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E element = node.getElement();
                node = node.next();
                return element;
            }
        };
    }

    @Override
    public @NotNull Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            private Node<E> node;

            {
                node = root == null ? null : root.rightmost();
            }

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E element = node.getElement();
                node = node.previous();
                return element;
            }
        };
    }

    @Override
    public @NotNull MyTreeSet<E> descendingSet() {
        return new MyTreeSetDescendingView<> (this);
    }

    private @NotNull Node<E> findElementOrLeaf(@NotNull Node<E> node, @NotNull E element) {
        Node<E> prev = node;
        while (node != null) {
            int compareRes = comparator.compare(element, node.getElement());
            prev = node;

            if (compareRes == 0) {
                return node;
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
            if (this.leftChild != null) {
                this.leftChild.parent = null;
            }

            if (leftChild != null) {
                leftChild.cutFromTree();
                leftChild.parent = this;
            }

            this.leftChild = leftChild;
        }

        public void setRightChild(@Nullable Node<E> rightChild) {
            if (this.rightChild != null) {
                this.rightChild.parent = null;
            }

            if (rightChild != null) {
                rightChild.cutFromTree();
                rightChild.parent = this;
            }

            this.rightChild = rightChild;

        }

        public boolean isLeftChild() {
            return parent != null && parent.getLeftChild() == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.getRightChild() == this;
        }

        public @NotNull Node<E> cutFromTree() {
            return replaceWith(null);
        }

        public @NotNull Node<E> replaceWith(@Nullable Node<E> node) {
            if (parent == null) {
                if (node != null) {
                    node.cutFromTree();
                }

                return this;
            }

            if (parent.getLeftChild() == this) {
                parent.setLeftChild(node);
            } else {
                parent.setRightChild(node);
            }

            parent = null;
            return this;
        }

        public @NotNull Node<E> leftmost() {
            Node<E> node = this;
            while (node.getLeftChild() != null) {
                node = node.getLeftChild();
            }

            return node;
        }

        public @NotNull Node<E> rightmost() {
            Node<E> node = this;
            while (node.getRightChild() != null) {
                node = node.getRightChild();
            }

            return node;
        }

        public @Nullable Node<E> previous() {
            if (leftChild != null) {
                return leftChild.rightmost();
            }

            Node<E> node = this;
            while (node.isLeftChild()) {
                node = node.getParent();
            }

            return node.parent;
        }

        public @Nullable Node<E> next() {
            if (rightChild != null) {
                return rightChild.leftmost();
            }

            Node<E> node = this;
            while (node.isRightChild()) {
                node = node.getParent();
            }

            return node.parent;
        }
    }
}
