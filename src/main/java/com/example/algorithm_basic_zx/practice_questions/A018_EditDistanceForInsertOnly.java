package com.example.algorithm_basic_zx.practice_questions;

public class A018_EditDistanceForInsertOnly {
    public static void main(String[] args) {
        int[] A = {1, 3, 4, 2, 5, 6};
        int[] B = {3, 4, 6, 5, 7};
        int ans = minAdd( A, B, 1, 1 );
        System.out.println( ans );
    }

    private static int minAdd(int[] a, int[] b, int del, int add) {
        if (a == null && b == null) {
            return 0;
        }
        if (a != null && b == null) {
//            return a.length * del;
            return 0;
        }
        if (a == null) {
            return b.length * add;
        }

        int[][] dp = generateDp( a, b, del, add );
        return dp[a.length][b.length];
    }

    // dp[i][j] means from a (len i) to b (len j) required minimum add
    private static int[][] generateDp(int[] a, int[] b, int del, int add) {
        int M = a.length + 1;
        int N = b.length + 1;
        int[][] dp = new int[M][N];
        for (int i = 0; i < M; i++) {
//            dp[i][0] = i * del;
            dp[i][0] = 0;
        }
        for (int j = 0; j < N; j++) {
            dp[0][j] = j * add;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p1;
                if (a[i - 1] == b[j - 1]) {
                    p1 = dp[i - 1][j - 1];
                } else {
//                    p1 = dp[i - 1][j - 1] + del + add;
                    p1 = dp[i - 1][j - 1] + 0 + add;
                }

                int p2 = dp[i][j - 1] + add;
//                int p3 = dp[i - 1][j] + del;
                int p3 = dp[i - 1][j] + 0;
                dp[i][j] = Math.min( Math.min( p1, p2 ), p3 );
            }

        }
        return dp;
    }


}
