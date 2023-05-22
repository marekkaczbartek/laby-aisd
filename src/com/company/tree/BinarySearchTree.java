package com.company.tree;

import com.company.exceptions.DuplicateElementException;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        private T value;
        private Node left;
        private Node right;

        private Node parent;

        public Node(T value, Node left, Node right, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        public Node(Node parent) {
            this.parent = parent;
            this.value = null;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int compareTo(Node node) {
            return this.getValue().compareTo(node.getValue());
        }
    }

    private Node root;

    public BinarySearchTree() {this.root = new Node(null);}

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node getNode(T value) {
        Node currentNode = this.root;
        Node parent = null;
        while (currentNode != null && currentNode.getValue() != null) {
            parent = currentNode;
            switch (currentNode.getValue().compareTo(value)) {
                case 0 -> {
                    return currentNode;
                }
                case 1 -> {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        currentNode = new Node(parent);
                        parent.setLeft(currentNode);
                    }
                }
                case -1 -> {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        currentNode = new Node(parent);
                        parent.setRight(currentNode);
                    }
                }
            }
        }
        return currentNode;
    }

    private T minimum(Node node) {
        Node currentNode = node.getRight();
        while (currentNode.getLeft() != null && currentNode.getLeft().getValue() != null) {
            currentNode = currentNode.getLeft();
        }
        return currentNode.getValue();
    }

    private T getNext(T value) {
        Node node = getNode(value);
        if (node.getRight() != null && node.getRight().getValue() != null) {
            return minimum(node);
        }
        Node current = node;
        while (current.getParent() != null && current.getParent().compareTo(node) == -1) {
            current = current.getParent();
        }
        if (current.getParent() == null) {
            return null;
        }
        else {
            return current.getParent().getValue();
        }
    }

    public void add(T value) throws DuplicateElementException {
        // TODO: Dodawanie nowej wartości do drzewa. Rzuć DuplicateElementException, jeśli element już istnieje.
        if (this.root.getValue() == null) {
            this.root.setValue(value);
            return;
        }
        Node node = this.getNode(value);
        if (node.getValue() == null) {
            node.setValue(value);
        }
        else {
            throw new DuplicateElementException();
        }
    }

    public boolean contains(T value) {
        // TODO: Sprawdzenie, czy drzewo zawiera podaną wartość.
        if (this.root.getValue() == null) {
            return false;
        }
        Node node = this.getNode(value);
        return (node.getValue() != null);
    }

    public void delete(T value) {
        // TODO: Usunięcie wskazanej wartości z drzewa.
        Node node = this.getNode(value);
        if (node.getValue() == null) {
            return;
        }
        else if (node.getLeft() == null && node.getRight() == null) {
            node.setValue(null);
        }
        else if (node.getLeft() == null || node.getLeft().getValue() == null) {
            Node parent = node.getParent();
            if (parent.compareTo(node) == 1) {
                parent.setLeft(node.getRight());
            }
            else {
                parent.setRight(node.getRight());
            }
            node.getRight().setParent(parent);
        }
        else if (node.getRight() == null || node.getRight().getValue() == null) {
            Node parent = node.getParent();
            if (parent.compareTo(node) == 1) {
                parent.setLeft(node.getLeft());
            }
            else {
                parent.setRight(node.getLeft());
            }
            node.getLeft().setParent(parent);
        }
        else {
            T next = getNext(value);
            delete(next);
            node.setValue(next);
        }
    }

    private String toStringPreOrder(Node node) {
        String result = "";
        if (node != null && node.getValue() != null) {
            result = result + ", " + node.getValue();
            result += toStringPreOrder(node.getLeft());
            result += toStringPreOrder(node.getRight());
        }
        return result;
    }

    public String toStringPreOrder() {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą pre-order.
        String result = toStringPreOrder(root);
        return result.length() > 0 ? result.substring(2) : result;
    }

    private String toStringInOrder(Node node) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        String result = "";
        if (node != null && node.getValue() != null) {
            result += toStringInOrder(node.getLeft());
            result = result + ", " + node.getValue();
            result += toStringInOrder(node.getRight());
        }
        return result;
    }

    private List<T> toListInOrder(Node node) {
        List<T> result = new ArrayList<>();
        if (node != null && node.getValue() != null) {
            result.addAll(toListInOrder(node.getLeft()));
            result.add(node.getValue());
            result.addAll(toListInOrder(node.getRight()));
        }
        return result;
    }

    public String toStringInOrder() {
        String result = toStringInOrder(root);
        return result.length() > 0 ? result.substring(2) : result;
    }

    public List<T> toListInOrder() {
        return toListInOrder(root);
    }

    private String toStringPostOrder(Node node) {
        // TODO: Zwróć wartość String reprezentującą drzewo po przejściu metodą in-order.
        String result = "";
        if (node != null && node.getValue() != null) {
            result += toStringPostOrder(node.getLeft());
            result += toStringPostOrder(node.getRight());
            result = result + ", " + node.getValue();
        }
        return result;
    }
    public String toStringPostOrder() {
        String result = toStringPostOrder(root);
        return result.length() > 0 ? result.substring(2) : result;
    }
}
