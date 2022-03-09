package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;

//https://leetcode.com/problems/next-permutation/
public class A012_NextPermutation {
    public static void main(String[] args) {
        int[] arr = {5, 1, 1};
        nextPermutation( arr );
        System.out.println( Arrays.toString( arr ) );
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int i = nums.length - 1;
        while ((i - 1) >= 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i < 1) {
            reverse( nums, 0, nums.length - 1 );
            return;
        }
        int j = i + 1;
        while (j <= (nums.length - 1) && nums[j] > nums[i - 1]) {
            j++;
        }
        int swapIndex = j - 1;
        swap( nums, i - 1, swapIndex );
        reverse( nums, i, nums.length - 1 );

    }

    public static void reverse(int[] nums, int L, int R) {
        while (L < R) {
            swap( nums, L, R );
            L++;
            R--;
        }
    }

    private static void swap(int[] nums, int L, int R) {
        int temp = nums[L];
        nums[L] = nums[R];
        nums[R] = temp;
    }
}
