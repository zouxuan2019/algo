package com.example.algorithm_basic_zx.practice_questions;

//https://leetcode.com/problems/count-primes/
public class A0053_CountPrimes {
    public static void main(String[] args) {
        System.out.println( countPrimes( 30 ) );
    }

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] f = new boolean[n];
        int count = n / 2; // remove odd numbers
        // skip 1,2,
        for (int i = 3; i * i < n; i += 2) {
            System.out.println( "i:" + i );
            if (f[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                System.out.println( "j:" + j );
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;

    }
}
