package com.example.algorithm_basic_zx.practice_questions;

public class T004_SumLR {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 6, 9, 2, 1, 3, 7};
        int length = arr.length;
        int[] helper = new int[length];
        helper[0] = arr[0];
        for (int i = 1; i < length; i++) {
            helper[i] = helper[i - 1] + arr[i];
        }
        System.out.println( sum( helper, 1, 5 ) );
        System.out.println( sum( helper, 0, 2 ) );


    }

    private static int sum(int[] helper, int l, int r) {
        if (l == 0) {
            return helper[r];
        }
        return helper[r] - helper[l - 1];
    }
}
