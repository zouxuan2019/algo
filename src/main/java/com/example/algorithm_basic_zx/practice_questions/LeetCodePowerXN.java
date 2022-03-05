package com.example.algorithm_basic_zx.practice_questions;

public class LeetCodePowerXN {
    public static void main(String[] args) {
        System.out.println( power( 2, 29) );
    }

    public static double power(int x, int n) {
        int p = n == Integer.MIN_VALUE ? (n + 1) : n;
        p = Math.abs( p );
        double val = positivePower( x, p );
        if (n == Integer.MIN_VALUE) {
            return 1.0D / (val * n);
        }
        return (n < 0) ? (1.0D / val) : val;

    }

    public static double positivePower(int x, int n) {
        int t = x;
        double ans = 1D;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans *= t;
            }
            n = n >> 1;
            t *= t;
        }
        return ans;
    }
}
