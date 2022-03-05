package com.example.algorithm_basic_zx.sorting;

import com.example.algorithm_basic_zx.Utils;

import java.util.Arrays;


public class T007_MergeSort {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Utils.generateRandomArray( maxLen, maxValue );
            int[] copyArr = Utils.copyArr( arr );
            mergeSort( arr );
            int[] arr2 = Arrays.stream( copyArr ).sorted().toArray();

            if (!Utils.isArrEqual( arr, arr2 )) {
                System.out.println( "Oops" );
                break;
            }
        }
        System.out.println( "Yeah!!" );
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process( arr, 0, arr.length - 1 );
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process( arr, L, M );
        process( arr, M + 1, R );
        merge( arr, L, M, R );
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] helper = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = M + 1;
        int i = 0;
        while (leftIndex <= M && rightIndex <= R) {
            if (arr[leftIndex] <= arr[rightIndex]) {
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
    }
}
