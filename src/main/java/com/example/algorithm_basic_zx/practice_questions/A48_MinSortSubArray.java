package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class A48_MinSortSubArray {


    public static void main(String[] args) {
        int[] arr = {2, 6, 4, 8, 10, 9, 15};
        System.out.println( findUnsortedSubarray( arr ) );
    }

    public static int findUnsortedSubarray(int[] nums) {
        int N = nums.length;
        int right = 0;
        int leftMax = nums[0];
        for (int i = 1; i < N; i++) {
            if (nums[i] < leftMax) {
                right = i;
            }
            leftMax = Math.max( leftMax, nums[i] );
        }

        int left = N - 1;
        int rightMin = nums[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] > rightMin) {
                left = i;
            }
            rightMin = Math.min( rightMin, nums[i] );
        }
        return right <= left ? 0 : right - left + 1;
    }
}
