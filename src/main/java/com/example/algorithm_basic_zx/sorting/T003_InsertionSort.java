package com.example.algorithm_basic_zx.sorting;

import com.example.algorithm_basic_zx.Utils;

import java.util.Arrays;

import static com.example.algorithm_basic_zx.Utils.swap;

public class T003_InsertionSort {

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Utils.generateRandomArray( maxLen, maxValue );
            int[] copyArr = Utils.copyArr( arr );
            insertionSort( arr );
            int[] arr2 = Arrays.stream( copyArr ).sorted().toArray();

            if (!Utils.isArrEqual( arr, arr2 )) {
                System.out.println( "Oops" );
                break;
            }
        }
        System.out.println( "Yeah!!" );
    }

    private static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j > 0 && arr[j] < arr[j - 1]) {
                j--;
                swap( arr, j, j - 1 );
            }
        }
    }
}
