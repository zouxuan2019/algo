package com.example.algorithm_basic_zx.dp;

//https://leetcode.com/problems/house-robber/
public class A002_HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max( nums[0], nums[1] );
        for (int i = 2; i < n; i++) {
            int p1 = nums[i];
            int p2 = dp[i - 1];
            int p3 = nums[i] + dp[i - 2];
            dp[i] = Math.max( Math.max( p1, p2 ), p3 );
        }
        return dp[n - 1];
    }
}
