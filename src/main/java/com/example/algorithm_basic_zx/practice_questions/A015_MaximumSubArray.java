package com.example.algorithm_basic_zx.practice_questions;

public class A015_MaximumSubArray {
    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println( maxSubArray( arr ) );
    }

    public static int maxSubArray(int[] nums) {
        int cur = 0;
        int pre = nums[0];
        int max = pre;
        for(int i = 1; i < nums.length; i++) {
            cur = nums[i];
            pre = Math.max( cur + pre, cur );
            max = Math.max( max,pre );
        }
        return max;
    }
}
