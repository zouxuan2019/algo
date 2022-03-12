package com.example.algorithm_basic_zx.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://leetcode.com/problems/freedom-trail/
public class A53_FreedomTrail {

    public static int findRotateSteps(String ring, String key) {
        if (ring == null || key == null || ring.length() == 0 || key.length() == 0) {
            return 0;
        }
        HashMap<Character, List<Integer>> positionMap = new HashMap<>();
        char[] chars = ring.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            List<Integer> position = positionMap.get( chars[i] );
            if (position == null) {
                position = new ArrayList<>();
            }
            position.add( i );
            positionMap.put( chars[i], position );
        }
        char[] keyChars = key.toCharArray();
        int ringSize = chars.length;
        int[][] dp = new int[ringSize][key.length()];
        for (int i = 0; i < ringSize; i++) {
            for (int j = 0; j < key.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return process( 0, keyChars, 0, positionMap, ringSize, dp );
    }

    public static int process(int pre, char[] key, int keyIndex, HashMap<Character, List<Integer>> positionMap, int ringSize, int[][] dp) {
        if (keyIndex == key.length) {
            return 0;
        }

        if (dp[pre][keyIndex] != -1) {
            return dp[pre][keyIndex];
        }

        char aim = key[keyIndex];
        List<Integer> position = positionMap.get( aim );
        int ans = Integer.MAX_VALUE;
        for (Integer next : position) {
            int p1 = dial( pre, next, ringSize ) + 1 + process( next, key, keyIndex + 1, positionMap, ringSize, dp );
            ans = Math.min( ans, p1 );
        }
        dp[pre][keyIndex] = ans;
        return ans;
    }

    public static int dial(int i1, int i2, int size) {
        int p1 = Math.abs( i1 - i2 );
        int p2 = size + Math.min( i1, i2 ) - Math.max( i1, i2 );
        return Math.min( p1, p2 );
    }

    public static void main(String[] args) {

    }
}
