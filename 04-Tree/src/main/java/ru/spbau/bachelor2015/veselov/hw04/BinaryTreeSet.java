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
     */
    public void add(@NotNull T newElement) {
        if (root == null) {
            root = new Node<T>(newElement);
            size = 1;
            return;
        }

        Node<T> node = root;
        while (true) {
            int compareResult = node.getElement().compareTo(newElement);
            if (compareResult == 0) {
                break;
            } else if (compareResult < 0) {
                if (node.getRightChild() == null) {
                    node.setRightChild(new Node<T>(newElement));
                    ++size;
                    break;
                }

                node = node.getRightChild();
            } else {
                if (node.getLeftChild() == null) {
                    node.setLeftChild(new Node<T>(newElement));
                    ++size;
                    break;
                }

                node = node.getLeftChild();
            }
        }
    }

    public boolean contains(@NotNull T element) {
        return false;
    }

    /**
     * @return number of elements currently stored in set
     */
    public int size() {
        return size;
    }

    static class Node<T extends Comparable<? super T>> {
        private @NotNull T element;
        Node<T> leftChild = null;
        Node<T> rightChild = null;

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
