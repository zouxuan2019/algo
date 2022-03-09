package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;
import java.util.TreeMap;

public class A020_LeetCodeMaxProfit2 {
    public static void main(String[] args) {
//        int[] startTime = {43, 13, 36, 31, 40, 5, 47, 13, 28, 16, 2, 11};
//        int[] endTime = {44, 22, 41, 41, 47, 13, 48, 35, 48, 26, 21, 39};
//        int[] profit = {8, 20, 3, 19, 16, 8, 11, 13, 2, 15, 1, 1};

        int[] startTime = {1, 2, 3, 3};
        int[] endTime = {3, 4, 4, 6};
        int[] profit = {50, 2000, 40, 70};

        System.out.println( jobScheduling( startTime, endTime, profit ) );
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort( jobs, (a, b) -> a[1] - b[1] );

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put( 0, 0 );
        for (int[] job : jobs) {
            int val = job[2] + dp.floorEntry( job[0] ).getValue();
            if (val > dp.lastEntry().getValue()) {
                dp.put( job[1], val );
            }
        }
        return dp.lastEntry().getValue();
    }
}
