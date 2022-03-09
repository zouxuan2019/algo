package com.example.algorithm_basic_zx.practice_questions;

import java.util.Arrays;

//Return line number of points that L can cover
public class A040_ArrCoverByLine {
    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max( max, i - pre );
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort( ans );
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println( "测试开始" );
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray( len, max );
            int ans1 = max1( arr, L );
            int ans2 = max2( arr, L );
            int ans3 = test( arr, L );
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println( ans1 );
                System.out.println( ans2 );
                System.out.println( ans3 );
                System.out.println( "oops!" );
                break;
            }
        }
        System.out.println( "测试ENd" );

    }

    private static int max1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex( arr, i, arr[i] - k );
            max = Math.max( max, i - nearest + 1 );
        }
        return max;
    }

    private static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    private static int max2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int L = 0, R = 0;
        int max = 0;
        int N = arr.length;
        for (; L < N; L++) {
            while (R < N && arr[R] <= arr[L] + k) {
                R++;
            }
            max = Math.max( max, (R - L) );
        }

        return max;
    }
}
