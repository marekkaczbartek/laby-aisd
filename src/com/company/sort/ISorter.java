package com.company.sort;

// Proszę nie modyfikować tego pliku!
public interface ISorter {

    default void swap(int[] values, int i1, int i2) {
        int temp = values[i1];
        values[i1] = values[i2];
        values[i2] = temp;
    }
    void sort(int[] values);
}
