package com.example.algorithm_basic_zx.sorting;

import com.example.algorithm_basic_zx.Utils;

import java.util.Arrays;

public class T013_HeapSort {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Utils.generateRandomArray( maxLen, maxValue );
            int[] copyArr = Utils.copyArr( arr );
            heapSort( arr );
            int[] arr2 = Arrays.stream( copyArr ).sorted().toArray();

            if (!Utils.isArrEqual( arr, arr2 )) {
                System.out.println( Arrays.toString( arr ) );
                System.out.println( Arrays.toString( arr2 ) );
                System.out.println( "Oops" );
                break;
            }
        }
        System.out.println( "Yeah!!" );
    }

    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //O(N*LogN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert( arr,i );
//        }

        //O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify( arr, i, arr.length );
        }
        int heapSize = arr.length;
        Utils.swap( arr, 0, --heapSize );
        while (heapSize > 0) {
            heapify( arr, 0, heapSize ); // O(LogN)
            Utils.swap( arr, 0, --heapSize );
        }

    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            Utils.swap( arr, largest, index );
            index = largest;
            left = 2 * index + 1;
        }

    }
}
