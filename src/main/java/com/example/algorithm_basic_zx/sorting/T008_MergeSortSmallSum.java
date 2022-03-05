package com.example.algorithm_basic_zx.sorting;

public class T008_MergeSortSmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process( arr, 0, arr.length - 1 );
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process( arr, L, M ) +
                process( arr, M + 1, R ) +
                merge( arr, L, M, R );
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int ans = 0;
        int[] helper = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = M + 1;
        int i = 0;
        while (leftIndex <= M && rightIndex <= R) {
            if (arr[leftIndex] < arr[rightIndex]) { // when equal, copy right side
                ans += (R - rightIndex + 1) * arr[leftIndex];
                helper[i++] = arr[leftIndex++];
            } else {
                helper[i++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= M) {
            helper[i++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            helper[i++] = arr[rightIndex++];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[L + j] = helper[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] + " " );
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray( maxSize, maxValue );
            int[] arr2 = copyArray( arr1 );
            if (smallSum( arr1 ) != comparator( arr2 )) {
                succeed = false;
                printArray( arr1 );
                printArray( arr2 );
                break;
            }
        }
        System.out.println( succeed ? "Nice!" : "Fucking fucked!" );
    }

}
