package com.company.disjoinedset;
import com.company.exceptions.ItemOutOfRangeException;

public interface IDisjointSetStructure {
    default void checkSize(int index, int size) throws ItemOutOfRangeException{
        if (index >= size || index < 0) {
            throw new ItemOutOfRangeException();
        }
    }
    int findSet(int item) throws ItemOutOfRangeException;
    void union(int item1, int item2) throws ItemOutOfRangeException;
}
