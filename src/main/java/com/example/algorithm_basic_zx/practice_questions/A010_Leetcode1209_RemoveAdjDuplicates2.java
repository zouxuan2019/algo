package com.example.algorithm_basic_zx.practice_questions;

import java.util.Stack;

//https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/submissions/
//Array Burst
public class A010_Leetcode1209_RemoveAdjDuplicates2 {
    public static void main(String[] args) {
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println( removeDuplicates( s, k ) );
    }

    public static String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        Stack<Info> stack = new Stack<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                Info info = new Info( chars[i], 1 );
                stack.push( info );
            } else {
                Info peek = stack.peek();
                if (peek.c == chars[i]) {
                    peek.count++;
                    if (peek.count == k) {
                        stack.pop();
                    }
                } else {
                    Info info = new Info( chars[i], 1 );
                    stack.push( info );
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Info pop = stack.pop();
            for (int i = 0; i < pop.count; i++) {
                sb.append( pop.c );
            }
        }
        return sb.toString();
    }

    public static class Info {
        private Character c;
        private int count;

        public Info(Character c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}
