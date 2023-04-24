package com.company.sort;

public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    private void merge(int[] arr, int left, int middle, int right) {
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        int i, j;

        for (i = 0; i < leftSize; i++) {
            leftArray[i] = arr[left + i];
        }
        for (j = 0; j < rightSize; j++) {
            rightArray[j] = arr[middle + j + 1];
        }

        int k = left;
        i = 0;
        j = 0;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] > rightArray[j]) {
                arr[k] = rightArray[j];
                j++;
            }
            else {
                arr[k] = leftArray[i];
                i++;
            }
            k++;
        }

        while (i < leftSize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightSize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        for (int currentSize = 1; currentSize < values.length; currentSize *= 2) {
            for (int start = 0; start < values.length - 1; start += currentSize * 2) {
                int middle = Math.min(start + currentSize - 1, values.length - 1);
                int end = Math.min(start + currentSize * 2 - 1, values.length - 1);

                merge(values, start, middle, end);
            }
            checker.check(values);
        }
    }
}
