package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class A045_LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int X = matrix.length;
        int Y = matrix[0].length;
        int[][] dp = new int[X][Y];
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                ans = Math.max( ans, process( matrix, i, j, dp ) );
            }
        }
        return ans;
    }

    public int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int X = matrix.length;
        int Y = matrix[0].length;
        int p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            p1 = process( matrix, i - 1, j, dp );
        }
        if (i < X - 1 && matrix[i + 1][j] > matrix[i][j]) {
            p2 = process( matrix, i + 1, j, dp );
        }
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            p3 = process( matrix, i, j - 1, dp );
        }
        if (j < Y - 1 && matrix[i][j + 1] > matrix[i][j]) {
            p4 = process( matrix, i, j + 1, dp );
        }
        int ans = Math.max( Math.max( p1, p2 ), Math.max( p3, p4 ) ) + 1;
        dp[i][j] = ans;
        return ans;
    }
}
