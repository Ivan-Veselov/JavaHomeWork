package ru.spbau.bachelor2015.veselov.hw03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Trie structure which can maintain set of Strings and count Strings
 * which begin with a given prefix. Also supports serialization.
 */
public class Trie implements SelfSerializable {
    private Node rootNode = new Node(null);

    private static final String NULL_ARG_EXCEPTION_MSG = "Argument must not be null!";

    /**
     * Creates empty Trie.
     */
    public Trie() {
    }

    /**
     * Adds new string to Trie.
     * Complexity: linear in string length
     *
     * @param element string to add
     * @return true if string was added, false if given string already presented in trie
     */
    public boolean add(String element) {
        if (element == null) {
            throw new IllegalArgumentException(NULL_ARG_EXCEPTION_MSG);
        }

        Node node = retrieveNode(element, true);

        if (node.isTerminal()) {
            return false;
        }

        node.setTerminal(true);
        return true;
    }

    /**
     * Checks whether given string contained in Trie.
     * Complexity: linear in string length
     *
     * @param element string to check
     * @return true if given string contained in Trie, false otherwise
     */
    public boolean contains(String element) {
        if (element == null) {
            throw new IllegalArgumentException(NULL_ARG_EXCEPTION_MSG);
        }

        Node node = retrieveNode(element, false);
        return node != null && node.isTerminal();
    }

    /**
     * Removes given string from Trie.
     * Complexity: linear in string length
     *
     * @param element string to remove
     * @return true if Trie contained given string, false otherwise
     */
    public boolean remove(String element) {
        if (element == null) {
            throw new IllegalArgumentException(NULL_ARG_EXCEPTION_MSG);
        }

        Node node = retrieveNode(element, false);

        if (node == null || !node.isTerminal()) {
            return false;
        }

        node.setTerminal(false);
        return true;
    }

    /**
     * Complexity: constant
     *
     * @return number of string stored in Trie
     */
    public int size() {
        return rootNode.getNumberOfTerminalsInSubTree();
    }

    /**
     * Counts number of strings in Trie with a given prefix.
     * If trie contains string equal to argument then it also
     * will be counted.
     * Complexity: linear in prefix length
     *
     * @param prefix
     * @return number of strings with a given prefix
     */
    public int howManyStartsWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException(NULL_ARG_EXCEPTION_MSG);
        }

        Node node = retrieveNode(prefix, false);
        return node == null ? 0 : node.getNumberOfTerminalsInSubTree();
    }

    private Node retrieveNode(String element, boolean addNewNodes) {
        Node node = rootNode;

        for (int i = 0; i < element.length(); ++i) {
            Character character = element.charAt(i);
            if (!node.isChildNodeExist(character)) {
                if (addNewNodes) {
                    node.newChildNode(character);
                } else {
                    return null;
                }
            }

            node = node.getChildNode(character);
        }

        return node;
    }

    public void serialize(OutputStream out) throws IOException {
    }

    public void deserialize(InputStream in) throws IOException {
    }

    static private class Node {
        private Node parentNode;
        private HashMap<Character, Node> childNodes = new HashMap<>();
        private boolean isTerminal = false;
        private int numberOfTerminalsInSubTree = 0;

        public Node(Node parentNode) {
            this.parentNode = parentNode;
        }

        public boolean isChildNodeExist(Character edgeCharacter) {
            return childNodes.containsKey(edgeCharacter);
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
