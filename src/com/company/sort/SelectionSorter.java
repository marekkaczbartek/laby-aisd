package com.company.sort;

public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    private int findMin(int[] values, int start, int end) {
        int min = values[start];
        int index = start;
        for (int i = start+1; i < end; i++) {
            if (values[i] < min) {
                min = values[i];
                index = i;
            }
        }
        return index;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        for (int i = 0; i < values.length; i++) {
            int min = findMin(values, i, values.length);
            swap(values, i, min);
            checker.check(values);
        }
    }
}
