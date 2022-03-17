package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/edit-distance/
public class LeetCode72_EditDistance {
    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "abcefghi";
        System.out.println( minEdit( str1, str2, 1, 1, 1 ) );
    }

    public static int minEdit(String str1, String str2, int insScore, int delScore, int repScore) {
        if (str1 == null && str2 == null) {
            return 0;
        }
        if (str1 == null) {
            return insScore * str2.length();
        }
        if (str2 == null) {
            return delScore * str2.length();
        }
        int[][] dp = process( str1, str2, insScore, delScore, repScore );
        return dp[str1.length()][str2.length()];
    }

    public static int[][] process(String str1, String str2, int insScore, int delScore, int repScore) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int M = arr1.length + 1;
        int N = arr2.length + 1;
        int[][] dp = new int[M][N]; // dp[i][j] str1 until i ==> str2 until j , get min cost
        for (int i = 0; i < M; i++) {
            dp[i][0] = i * delScore;
        }
        for (int j = 0; j < N; j++) {
            dp[0][j] = j * delScore;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p1;
                if (arr1[i - 1] == arr2[j - 1]) {
                    p1 = dp[i - 1][j - 1];
                } else {
                    p1 = dp[i - 1][j - 1] + repScore;
                }

                int p2 = dp[i - 1][j] + delScore;
                int p3 = dp[i][j - 1] + insScore;
                dp[i][j] = Math.min( Math.min( p1, p2 ), p3 );
            }

        }
        return dp;

    }
}
