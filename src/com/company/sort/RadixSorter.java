package com.company.sort;

public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    public int arrMax(int[] arr) {
        int res = 0;
        for (int num : arr) {
            res = Math.max(num, res);
        }
        return res;
    }

    public void countSort(int[] arr, int e) {
        int m = arrMax(arr);
        int[] counts = new int[m + 1];
        int[] out = new int[arr.length];

        for (int num : arr) {
            counts[(num / e) % 10]++;
        }
        for (int i = 1; i < m - 1; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            out[counts[(arr[i] / e) % 10]-1] = arr[i];
            counts[(arr[i] / e) % 10]--;
        }
        System.arraycopy(out, 0, arr, 0, arr.length);
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        int m = arrMax(values);
        for (int e = 1; m / e > 0; e *= 10) {
            countSort(values, e);
            checker.check(values);
        }
    }
}
