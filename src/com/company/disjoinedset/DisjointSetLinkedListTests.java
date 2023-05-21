package com.company.disjoinedset;

public class DisjointSetLinkedListTests extends DisjointSetTestBase {
    protected DisjointSetLinkedListTests() {
        super(size -> new DisjointSetLinkedList(size));
    }
}
