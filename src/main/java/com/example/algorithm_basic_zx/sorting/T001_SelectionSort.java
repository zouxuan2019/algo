package com.example.algorithm_basic_zx.sorting;

import com.example.algorithm_basic_zx.Utils;

import java.util.Arrays;

import static com.example.algorithm_basic_zx.Utils.swap;

public class T001_SelectionSort {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Utils.generateRandomArray( maxLen, maxValue );
            int[] copyArr = Utils.copyArr( arr );
            selectionSort( arr );
            int[] arr2 = Arrays.stream( copyArr ).sorted().toArray();

            if (!Utils.isArrEqual( arr, arr2 )) {
                System.out.println( "Oops" );
                break;
            }
        }
        System.out.println( "Yeah!!" );
    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = 0; j < i + 1; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            swap( arr, max, i );
        }

    }



}
