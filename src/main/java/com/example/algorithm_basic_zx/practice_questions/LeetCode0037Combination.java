package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;

public class LeetCode0037Combination {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int target = 4;
        System.out.println( combinationSum4( nums, target ) );
    }

    public static int combinationSum4(int[] nums, int target) {
        int max = 1001;
        int[] dp1 = new int[max];
        Arrays.fill( dp1, 0, target + 1, -1 );
        return ways( target, nums, dp1 );
    }

    public static int ways(int rest, int[] nums, int[] dp) {
        if (rest < 0) {
            return 0;
        }
        if (dp[rest] != -1) {
            return dp[rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = 1;
        } else {
            for (int num : nums) {
                ans += ways( rest - num, nums, dp );
            }
        }
        dp[rest] = ans;
        return ans;
    }
}
