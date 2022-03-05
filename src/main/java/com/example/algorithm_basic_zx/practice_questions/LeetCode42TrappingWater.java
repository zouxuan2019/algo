package com.example.algorithm_basic_zx.practice_questions;

public class LeetCode42TrappingWater {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(trap(arr));


    }

    public static int trap(int[] height) {

        if (height == null || height.length < 3) {
            return 0;
        }
        int len = height.length;
        int water = 0;
        int l = 1;
        int r = len - 2;
        int lMax = height[0];
        int rMax = height[len - 1];

        while (l <= r) {
            if (lMax <= rMax) {
                water += Math.max(0, lMax - height[l]);
                lMax = Math.max(lMax, height[l++]);
            } else {
                water += Math.max(0, rMax - height[r]);
                rMax = Math.max(rMax, height[r--]);
            }
        }
        return water;
    }
}
