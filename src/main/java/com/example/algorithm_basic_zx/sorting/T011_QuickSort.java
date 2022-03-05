package com.example.algorithm_basic_zx.sorting;

import com.example.algorithm_basic_zx.Utils;

import java.util.Arrays;

public class T011_QuickSort {
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Utils.generateRandomArray( maxLen, maxValue );
            int[] copyArr = Utils.copyArr( arr );
            quickSort( arr );
            int[] arr2 = Arrays.stream( copyArr ).sorted().toArray();

            if (!Utils.isArrEqual( arr, arr2 )) {
                System.out.println( "Oops" );
                break;
            }
        }
        System.out.println( "Yeah!!" );
    }

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process( arr, 0, arr.length - 1 );
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int random = L + (int) (Math.random() * (R - L + 1));
        Utils.swap( arr, random, R );
        int[] equal = netherlandsFlag( arr, L, R );
        process( arr, L, equal[0] - 1 );
        process( arr, equal[1] + 1, R );
    }


    private static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }

        int[] ans = new int[2];
        int pivot = arr[R];
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == pivot) {
                index++;
            } else if (arr[index] < pivot) {
                Utils.swap( arr, index++, ++less );
            } else {
                Utils.swap( arr, index, --more );
            }
        }
        Utils.swap( arr, more, R );

        ans[0] = less + 1;
        ans[1] = more;
        return ans;
    }
}
