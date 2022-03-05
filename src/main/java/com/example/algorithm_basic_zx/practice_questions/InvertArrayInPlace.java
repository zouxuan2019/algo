package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;

public class InvertArrayInPlace {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int l = 0, r = arr.length - 1;
        while (l < r) {
            swap( arr, l, r );
            l++;
            r--;
        }
        System.out.println( Arrays.toString( arr ) );
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
