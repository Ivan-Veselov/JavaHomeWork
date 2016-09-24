package ru.spbau.bachelor2015.veselov.hw03;

import java.util.HashMap;

/**
 * Trie structure which can maintain set of Strings and count Strings
 * which begin with a given prefix. Also supports serialization.
 */
public class Trie {
    private Node rootNode;

    public boolean add(String element) {
        return false;
    }

    public boolean contains(String element) {
        return false;
    }

    public boolean remove(String element) {
        return false;
    }

    public int size() {
        return 0;
    }

    public int howManyStartsWithPrefix(String prefix) {
        return 0;
    }

    static private class Node {
        private Node parentNode;
        private HashMap<Character, Node> childNodes = new HashMap<>();
        private boolean isTerminal = false;
        private int numberOfTerminalsInSubTree = 0;

        public Node(Node parentNode) {
            this.parentNode = parentNode;
        }

        public Node getChildNode(Character edgeCharacter) {
            return childNodes.get(edgeCharacter);
        }

        public Node newChildNode(Character edgeCharacter) {
            Node child = new Node(this);
            childNodes.put(edgeCharacter, child);

            return child;
        }

        public void setTerminal(boolean isTerminal) {
            if (this.isTerminal == isTerminal) {
                return;
            }

            this.isTerminal = isTerminal;
            final int alteration = isTerminal ? 1 : -1;

            Node node = this;
            while (node != null) {
                node.numberOfTerminalsInSubTree += alteration;
                node = node.parentNode;
            }
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public int getNumberOfTerminalsInSubTree() {
            return numberOfTerminalsInSubTree;
        }
    }
}
