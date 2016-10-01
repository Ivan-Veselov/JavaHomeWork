package ru.spbau.bachelor2015.veselov.hw04;

import org.jetbrains.annotations.NotNull;

public class BinaryTreeSet<T extends Comparable<? super T>> {
    public BinaryTreeSet() {
    }

    public void add(@NotNull T newElement) {
    }

    public boolean contains(@NotNull T element) {
        return false;
    }

    public int size() {
        return 0;
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
