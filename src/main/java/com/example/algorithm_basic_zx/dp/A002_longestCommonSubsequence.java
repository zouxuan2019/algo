package com.example.algorithm_basic_zx.dp;

public class A002_longestCommonSubsequence {
    public static int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }

        }
        return process( str1, str2, str1.length - 1, str2.length - 1, dp );
    }

    public static int process(char[] str1, char[] str2, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == 0 && j == 0) {
            int ans = (str1[i] == str2[j]) ? 1 : 0;
            dp[i][j] = ans;
            return ans;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                dp[i][j] = 1;
                return 1;
            } else {
                int ans = process( str1, str2, i, j - 1, dp );
                dp[i][j] = ans;
                return ans;
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                dp[i][j] = 1;
                return 1;
            } else {
                int ans = process( str1, str2, i - 1, j, dp );
                dp[i][j] = ans;
                return ans;
            }
        } else {
            int p2 = process( str1, str2, i - 1, j, dp );
            int p4 = process( str1, str2, i, j - 1, dp );
            int p3 = str1[i] == str2[j] ? (1 + process( str1, str2, i - 1, j - 1, dp )) : 0;
            int ans = Math.max( p2, Math.max( p3, p4 ) );
            dp[i][j] = ans;
            return ans;
        }
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int M = str1.length;
        int N = str2.length;
        int[][] dp = new int[M][N];
        dp[0][0] = (str1[0] == str2[0]) ? 1 : 0;
        for (int j = 1; j < N; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i < M; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p2 = dp[i - 1][j];
                int p4 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max( p2, Math.max( p3, p4 ) );
            }
        }
        return dp[M - 1][N - 1];
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
        System.out.println( longestCommonSubsequence( str1, str2 ) );
    }
}
