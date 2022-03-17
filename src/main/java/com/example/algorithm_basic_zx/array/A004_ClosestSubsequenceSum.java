package com.example.algorithm_basic_zx.array;

import java.util.Arrays;

// 本题测试链接 : https://leetcode.com/problems/closest-subsequence-sum/
// 本题数据量描述:
// 1 <= nums.length <= 40
// -10^7 <= nums[i] <= 10^7
// -10^9 <= goal <= 10^9
// 通过这个数据量描述可知，需要用到分治，因为数组长度不大
// 而值很大，用动态规划的话，表会爆
public class A004_ClosestSubsequenceSum {
    public static int[] l1 = new int[20];
    public static int[] r1 = new int[20];

    public static int[] l = new int[20];
    public static int[] r = new int[20];

    public static void main(String[] args) {
//        int[] arr = {5, -7, 3, 5};
//        System.out.println( minAbsDifference( arr, 6 ) );
        int[] arr =
                {-7933, -1642, -6137, 6234, 4728, 5474, 2439};
        int goal = -428059487;
        System.out.println( minAbsDifference1( arr, goal ) );
        System.out.println( minAbsDifference( arr, goal ) );
    }

    public static int minAbsDifference1(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int N = nums.length;

        int le = processSum( nums, 0, nums.length >> 1, 0, 0, l1 ); // [index, R), not include R
        int re = processSum( nums, nums.length >> 1, nums.length, 0, 0, r1 );

//        int le = process( nums, 0, nums.length >> 1, 0, 0, l1 );
//        int re = process( nums, nums.length >> 1, nums.length, 0, 0, r1 );
        Arrays.sort( l1, 0, le );
        Arrays.sort( r1, 0, re-- );
        System.out.println( "1-------------------" );
        System.out.println( Arrays.toString( l1 ) );
        System.out.println( Arrays.toString( r1 ) );

        int ans = Math.abs( goal );
        for (int i = 0; i < le; i++) {
            int rest = goal - l1[i];
            while (re > 0 && Math.abs( rest - r1[re - 1] ) <= Math.abs( rest - r1[re] )) {
                re--;
            }
            ans = Math.min( ans, Math.abs( rest - r1[re] ) );
        }
        return ans;
    }

    public static int processSum(int[] nums, int index, int R, int sum, int fill, int[] result) {
        if (index == R) {
            result[fill++] = sum;
        } else {
            fill = processSum( nums, index + 1, R, sum, fill, result );
            fill = processSum( nums, index + 1, R, sum + nums[index], fill, result );
        }
        return fill;
    }

    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            fill = process( nums, index + 1, end, sum, fill, arr );
            fill = process( nums, index + 1, end, sum + nums[index], fill, arr );
        }
        return fill;
    }

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int le = process( nums, 0, nums.length >> 1, 0, 0, l );
        int re = process( nums, nums.length >> 1, nums.length, 0, 0, r );
        Arrays.sort( l, 0, le );
        Arrays.sort( r, 0, re-- );
        System.out.println( "1-------------------" );
        System.out.println( Arrays.toString( l ) );
        System.out.println( Arrays.toString( r ) );
        int ans = Math.abs( goal );
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs( rest - r[re - 1] ) <= Math.abs( rest - r[re] )) {
                re--;
            }
            ans = Math.min( ans, Math.abs( rest - r[re] ) );
        }
        return ans;
    }


}
