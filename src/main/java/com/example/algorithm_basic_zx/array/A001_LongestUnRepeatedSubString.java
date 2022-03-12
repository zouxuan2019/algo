package com.example.algorithm_basic_zx.array;

import java.util.Arrays;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class A001_LongestUnRepeatedSubString {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println( lengthOfLongestSubstring( str ) );
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] lastPosition = new int[256];
        Arrays.fill( lastPosition, -1 );
        int pre = 1;
        char[] chars = s.toCharArray();
        int max = 1;
        lastPosition[chars[0]] = 0;
        for (int i = 1; i < s.length(); i++) {
            int p1 = i - lastPosition[chars[i]] - 1;
            int p2 = pre;
            pre = Math.min( p1, p2 ) + 1;
            max = Math.max( max, pre );
            lastPosition[chars[i]] = i;
        }
        return max;
    }
}
