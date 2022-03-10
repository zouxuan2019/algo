package com.example.algorithm_basic_zx.dp;

public class A001_HouseRobberII {
    public static void main(String[] args) {

    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int p1 = rob1( nums, 0, n - 2 );
        int p2 = rob1( nums, 1, n - 1 );
        return Math.max( p1, p2 );

    }

    public int rob1(int[] nums, int L, int R) {
        int n = nums.length;
        if (L == R) {
            return nums[L];
        }

        int[] dp = new int[n];
        dp[L] = nums[L];
        dp[L + 1] = Math.max( nums[L], nums[L + 1] );
        for (int i = L + 2; i <= R; i++) {
            int p1 = nums[i];
            int p2 = dp[i - 1];
            int p3 = nums[i] + dp[i - 2];
            dp[i] = Math.max( Math.max( p1, p2 ), p3 );
        }
        return dp[R];
    }

    public int rob12(int[] nums, int L, int R) {
        if (L == R) {
            return nums[L];
        }

        int dpL = nums[L];
        int dpLPlusOne = Math.max( nums[L], nums[L + 1] );
        for (int i = L + 2; i <= R; i++) {
            int p1 = nums[i];
            int p2 = dpLPlusOne;
            int p3 = nums[i] + dpL;
            dpL = dpLPlusOne;
            dpLPlusOne = Math.max( Math.max( p1, p2 ), p3 );
        }
        return dpLPlusOne;
    }

}
