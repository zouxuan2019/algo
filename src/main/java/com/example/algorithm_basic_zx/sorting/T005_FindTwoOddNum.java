package com.example.algorithm_basic_zx.sorting;

public class T005_FindTwoOddNum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 0, 0, 0, -1, -1, 9};
        int[] twoOdd = findTwoOdd( arr );
        System.out.println( twoOdd[0] );
        System.out.println( twoOdd[1] );
    }

    public static int[] findTwoOdd(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        int rightMostOne = result & -result;
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightMostOne) != 0) {
                a = a ^ arr[i];
            }
        }
        int b = result ^ a;
        return new int[]{a, b};
    }
}
