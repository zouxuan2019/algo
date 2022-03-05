package com.example.algorithm_basic_zx.sorting;

public class T009_MergeSortReverserPair {

    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process( arr, 0, arr.length - 1 );
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process( arr, l, mid ) + process( arr, mid + 1, r ) + merge( arr, l, mid, r );
    }

    public static int merge(int[] arr, int L, int M, int R) {

        int ans = 0;
        int[] helper = new int[R - L + 1];
        int leftIndex = M;
        int rightIndex = R;
        int i = helper.length-1;
        while (leftIndex >= L && rightIndex > M) {
            if (arr[leftIndex] > arr[rightIndex]) { // when equal, copy right side
                ans += (rightIndex - M);
                helper[i--] = arr[leftIndex--];
            } else {
                helper[i--] = arr[rightIndex--];
            }
        }
        while (leftIndex >= L) {
            helper[i--] = arr[leftIndex--];
        }
        while (rightIndex > M) {
            helper[i--] = arr[rightIndex--];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[L + j] = helper[j];
        }
        return ans;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
        System.out.println( "测试开始" );
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray( maxSize, maxValue );
            int[] arr2 = copyArray( arr1 );
            if (reverPairNumber( arr1 ) != comparator( arr2 )) {
                System.out.println( "Oops!" );
                printArray( arr1 );
                printArray( arr2 );
                break;
            }
        }
        System.out.println( "测试结束" );
    }
}
