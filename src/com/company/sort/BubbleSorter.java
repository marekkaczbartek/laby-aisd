package com.company.sort;

public class BubbleSorter implements ISorter {
    private final IChecker checker;

    public BubbleSorter(IChecker checker) {
        this.checker = checker;
    }

    private void swap(int[] values, int i1, int i2) {
        int temp = values[i1];
        values[i1] = values[i2];
        values[i2] = temp;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        int lastSwapIndex = values.length-1;
        while (lastSwapIndex > 0) {
            int right = lastSwapIndex;
            lastSwapIndex = 0;
            for (int left = 0; left < right; left++) {
                if (values[left] > values[left+1]) {
                    swap(values, left, left+1);
                    lastSwapIndex = left+1;
                }
            }
            checker.check(values);
        }
    }
}
