package com.company.sort;

public class InsertionSorter implements ISorter {
    private final IChecker checker;

    public InsertionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        for (int i = 1; i < values.length; i++) {
            int j = i-1;
            while (j >= 0 && values[j] > values[j+1]) {
                swap(values, j, j+1);
                j--;
            }
            checker.check(values);
        }
    }
}
