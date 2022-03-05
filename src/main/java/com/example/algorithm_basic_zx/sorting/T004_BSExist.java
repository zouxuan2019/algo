package com.example.algorithm_basic_zx.sorting;

public class T004_BSExist {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5, 6, 8, 8, 9, 10};
        System.out.println( isExist( arr, 4 ) );
        System.out.println( isExist( arr, 1 ) );
        System.out.println( isExist( arr, 2 ) );
        System.out.println( isExist( arr, 10 ) );
        System.out.println( isExist( arr, 11 ) );
        System.out.println( isExist( arr, 7 ) );
    }

    public static boolean isExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            }
            if (num > arr[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return arr[L] == num;

    }
}
