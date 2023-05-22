package com.company.tree;

import com.company.exceptions.DuplicateElementException;

import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        // TODO: Posortuj listę używając klasy BinarySearchTree.
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        for (T element : list) {
            tree.add(element);
        }
        List<T> array = tree.toListInOrder();
        for (int i = 0; i < list.size(); i++) {
            list.set(i, array.get(i));
        }
    }
}
