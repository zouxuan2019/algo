package com.example.algorithm_basic_zx.sorting;

public class T005_FindOddNum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 0, 0, 0, -1, -1};
        System.out.println( findOdd( arr ) );
    }

    public static int findOdd(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        return result;
    }
}
