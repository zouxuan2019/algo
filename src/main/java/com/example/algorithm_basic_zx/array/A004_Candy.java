package com.example.algorithm_basic_zx.array;

import java.util.Arrays;

// 测试链接 : https://leetcode.com/problems/candy/
public class A004_Candy {
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Math.max( left[i], right[i] );
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 3, 3, 2, 1, 4, 2, 2, 5, 2, 1};
        int[] arr = {1, 0, 2};
        System.out.println( candy( arr ) );
    }
}
