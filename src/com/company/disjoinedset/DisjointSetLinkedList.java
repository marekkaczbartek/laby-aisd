package com.company.disjoinedset;

import com.company.exceptions.ItemOutOfRangeException;
public class DisjointSetLinkedList implements IDisjointSetStructure {
    private class Node {
        private int rep;
        private int rank;
        private int next;
        private int last;

        public Node(int rep) {
            this.rep = rep;
            this.rank = 0;
            this.next = -1;
            this.last = rep;
        }

        public int getRep() {
            return rep;
        }

        public void setRep(int rep) {
            this.rep = rep;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
    private int size;
    private Node[] list;

    public DisjointSetLinkedList(int size) {
        this.size = size;
        list = new Node[size];
        for (int i = 0; i < size; i++) {
            list[i] = new Node(i);
        }
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        checkSize(item, size);
        Node node = list[item];
        return node.rep;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        checkSize(item1, size);
        checkSize(item2, size);

        int rep1 = list[item1].getRep();
        int rep2 = list[item2].getRep();

        Node node1, node2;

        if (list[rep1].getRank() >= list[rep2].getRank()) {
            node1 = list[rep1];
            node2 = list[rep2];
        }
        else {
            node1 = list[rep2];
            node2 = list[rep1];
        }
        node1.setRank(node1.getRank() + node2.getRank() + 1);

        int last1 = node1.getLast();
        int last2 = node2.getLast();
        list[last1].setNext(rep2);
        Node current = list[rep2];
        while (current.getNext() != -1) {
            current.setRep(rep1);
            current = list[current.getNext()];
        }
        current.setRep(rep1);
        list[rep1].setLast(last2);
    }
}
