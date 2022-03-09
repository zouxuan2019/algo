package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class A33_SellStock_I {
    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        System.out.println( maxProfit( arr ) );

    }

    public static int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min( min, prices[i] );
            maxProfit = Math.max( maxProfit, prices[i] - min );
        }
        return maxProfit;
    }
}

