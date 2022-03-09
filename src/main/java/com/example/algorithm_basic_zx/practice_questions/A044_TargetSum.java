package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;
import java.util.HashMap;

//https://leetcode.com/problems/target-sum/
public class A044_TargetSum {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println( findTargetSumWays3( arr, target ) );
    }

    public static int findTargetSumWays1(int[] nums, int target) {
        return process1( nums, 0, target );
    }

    public static int process1(int[] nums, int i, int rest) {
        if (i == nums.length) {
            return rest == 0 ? 1 : 0;
        }
        int p1 = process1( nums, i + 1, rest + nums[i] );
        int p2 = process1( nums, i + 1, rest - nums[i] );
        return p1 + p2;
    }

    public static int findTargetSumWays2(int[] nums, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        return process2( nums, 0, target, dp );
    }

    public static int process2(int[] nums, int i, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        HashMap<Integer, Integer> map = dp.get( i );
        if (map != null) {
            Integer target = map.get( rest );
            if (target != null) {
                return target;
            }
        }
        if (i == nums.length) {
            return rest == 0 ? 1 : 0;
        }
        int p1 = process2( nums, i + 1, rest + nums[i], dp );
        int p2 = process2( nums, i + 1, rest - nums[i], dp );
        int result = p1 + p2;
        map = map == null ? new HashMap<>() : map;
        map.put( rest, result );
        dp.put( i, map );
        return result;
    }

    public static int findTargetSumWays3(int[] nums, int target) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (target > sum) {
            return 0;
        }
        if (((target & 1) ^ (sum & 1)) != 0) {
            return 0;
        }
        int p = (target + sum) / 2;
        if (p < 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n + 1][p + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= p; j++) {
                int p1 = dp[i - 1][j];
                int p2 = 0;
                if (j - nums[i - 1] >= 0) {
                    p2 = dp[i - 1][j - nums[i - 1]];
                }
                dp[i][j] = p1 + p2;
            }
        }
        return dp[n][p];
    }
}
