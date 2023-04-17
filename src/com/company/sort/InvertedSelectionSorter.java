package com.company.sort;

public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    private int findMax(int[] values, int start, int end) {
        int max = 0;
        int index = -1;
        for (int i = start; i < end; i++) {
            if (values[i] > max) {
                max = values[i];
                index = i;
            }
        }
        return index;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Zaimplementuj algorytm SelectionSort "w drugą stronę" (czyli przechodząc od drugiej strony tablicy)
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        for (int i = values.length-1; i >= 0; i--) {
            int max = findMax(values, 0, i+1);
            swap(values, i, max);
            checker.check(values);
        }
    }
}
