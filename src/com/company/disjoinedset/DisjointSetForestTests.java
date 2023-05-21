package com.company.disjoinedset;

public class DisjointSetForestTests extends DisjointSetTestBase {
    protected DisjointSetForestTests() {
        super(size -> new DisjointSetForest(size));
    }
}
