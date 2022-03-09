package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/most-profit-assigning-work/
public class A47_MaxMoney {
    public static class Job {
        public int profit;
        public int difficulty;

        public Job(int m, int h) {
            profit = m;
            difficulty = h;
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = difficulty.length;
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            Job job = new Job( profit[i], difficulty[i] );
            jobs[i] = job;
        }
        Arrays.sort( jobs, (a, b) -> {
            int diff = a.difficulty - b.difficulty;
            return diff == 0 ? b.profit - a.profit : diff;
        } );

        TreeMap<Integer, Integer> finalJobs = new TreeMap<>();
        finalJobs.put( jobs[0].difficulty, jobs[0].profit );
        Job pre = jobs[0];
        for (int i = 1; i < N; i++) {
            if ((jobs[i].difficulty != pre.difficulty) && (jobs[i].profit > pre.profit)) {
                finalJobs.put( jobs[i].difficulty, jobs[i].profit );
                pre = jobs[i];
            }
        }
        int all = 0;

        for (int j : worker) {
            Map.Entry<Integer, Integer> latestJob = finalJobs.floorEntry( j );
            if (latestJob != null) {
                all += latestJob.getValue();
            }
        }
        return all;
    }

    public static void main(String[] args) {
    }

}
