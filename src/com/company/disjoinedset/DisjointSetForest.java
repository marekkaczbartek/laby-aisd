package com.company.disjoinedset;

import com.company.exceptions.ItemOutOfRangeException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisjointSetForest implements IDisjointSetStructure {
    private class Node {
        private int parent;
        private int rank;

        public Node(int parent) {
            this.parent = parent;
            this.rank = 0;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }

    private int size;
    private Node[] tree;
    public DisjointSetForest(int size) {
        this.size = size;
        this.tree = new Node[size];
        for (int i = 0; i < size; i++) {
            tree[i] = new Node(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        checkSize(item, size);
        if (item != tree[item].getParent()) {
            tree[item].setParent(findSet(tree[item].getParent()));
        }
        return tree[item].getParent();
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        checkSize(item1, size);
        checkSize(item2, size);

        int rep1 = findSet(item1);
        int rep2 = findSet(item2);
        Node node1 = tree[rep1];
        Node node2 = tree[rep2];

        if (node1.getRank() >= node2.getRank()) {
            node2.setParent(rep1);
            if (node1.getRank() == node2.getRank()) {
                node1.setRank(node1.getRank() + 1);
            }
        }
        else {
            node1.setParent(rep2);

        }
    }

    public int getNumberOfSets() {
        Set<Integer> set = new HashSet<>();
        for (Node i : tree) {
            set.add(i.getParent());
        }
        return set.size();
    }
}
