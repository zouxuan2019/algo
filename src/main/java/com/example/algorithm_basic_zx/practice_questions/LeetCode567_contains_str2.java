package com.example.algorithm_basic_zx.practice_questions;

public class LeetCode567_contains_str2 {
    public static void main(String[] args) {
        String s1 = "abe", s2 = "eidbaooo";

        System.out.println(isContains(s2, s1));
    }

    private static boolean isContains(String s2, String s1) {
        if (s2.length() < s1.length()) {
            return false;
        }
        int all = s1.length();
        char[] debt = new char[256];
        for (int i = 0; i < all; i++) {
            debt[s1.charAt(i)]++;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (debt[s2.charAt(i)]-- > 0) {
                all--;
            }
        }
        if (all == 0) {
            return true;
        }

        int l = 0, r = s1.length();
        for (; r < s2.length(); r++, l++) {
            if (debt[s2.charAt(r)]-- > 0) {
                all--;
            }
            if (++debt[s2.charAt(l)] > 0) {
                all++;
            }
            if (all == 0) {
                return true;
            }
        }

        return false;
    }
}
