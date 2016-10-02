package ru.spbau.bachelor2015.veselov.hw04;

import org.jetbrains.annotations.NotNull;

/**
 * Class which represents sets of objects of given class. Implemented as
 * unbalanced BST.
 *
 * @param <T> a type of objects to store inside set
 */
public class BinaryTreeSet<T extends Comparable<? super T>> {
    private Node<T> root = null;
    private int size = 0;

    /**
     * Creates empty set.
     */
    public BinaryTreeSet() {
    }

    /**
     * Adds new element to set.
     *
     * @param newElement new element to add
     * @return true if new element was added, false if a given element has already been presented in set
     */
    public boolean add(@NotNull T newElement) {
        if (root == null) {
            root = new Node<T>(newElement);
            size = 1;
            return true;
        }

        Node<T> node = root;
        while (true) {
            int compareResult = node.getElement().compareTo(newElement);
            if (compareResult == 0) {
                return false;
            } else if (compareResult < 0) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new Node<T>(newElement));
                    ++size;
                    return true;
                }

                node = node.getRightChild();
            } else {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new Node<T>(newElement));
                    ++size;
                    return true;
                }

                node = node.getLeftChild();
            }
        }
    }

    /**
     * Checks whether given element is contained in set.
     *
     * @param element element which presence is checked
     * @return true if set contains given element, false otherwise
     */
    public boolean contains(@NotNull T element) {
        Node<T> node = root;

        while (node != null) {
            int compareResult = node.getElement().compareTo(element);
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                node = node.getRightChild();
            } else {
                node = node.getLeftChild();
            }
        }

        return false;
    }

    /**
     * @return number of elements currently stored in set
     */
    public int size() {
        return size;
    }

    private static class Node<T extends Comparable<? super T>> {
        private @NotNull T element;
        private Node<T> leftChild = null;
        private Node<T> rightChild = null;

        public Node(@NotNull T element) {
            this.element = element;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public T getElement() {
            return element;
        }
    }
}
