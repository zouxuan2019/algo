package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class A33_SellStock_II {
    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        System.out.println( maxProfit( arr ) );

    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max( 0, prices[i] - prices[i - 1] );
        }
        return ans;
    }
}

