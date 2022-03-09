package com.example.algorithm_basic_zx.practice_questions;

public class A031_MinimumDifferenceBetweenTwoSets {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 5};
//        int minDiff = minDiff( arr );
//        System.out.println( minDiff );

        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = minDiff(arr);
            int ans2 = minDiff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int minDiff(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int smallSetSum = process( sum / 2, arr, 0 );
        int bigSetSum = sum - smallSetSum;
        return bigSetSum - smallSetSum;
    }

    private static int process(int rest, int[] arr, int i) {
        if (i >= arr.length) {
            return 0;
        }
        int p1 = process( rest, arr, i + 1 );
        int p2 = 0;
        if (arr[i] <= rest) {
            p2 = arr[i] + process( rest - arr[i], arr, i + 1 );
        }
        return Math.max( p1, p2 );
    }

    private static int minDiff2(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max( p1, p2 );
            }
        }
        int smallSetSum = dp[0][sum / 2];
        int bigSetSum = sum - smallSetSum;
        return bigSetSum - smallSetSum;
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
