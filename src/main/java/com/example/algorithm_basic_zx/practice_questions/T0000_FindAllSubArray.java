package com.example.algorithm_basic_zx.practice_questions;

import java.util.ArrayList;
import java.util.List;

public class T0000_FindAllSubArray {
    public static void main(String[] args) {
        String input = "abc";
        char[] chars = input.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        find( chars, 0, ans, path );
        for (String a : ans) {
            System.out.println( a );
        }
    }

    public static void find(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add( path );
            return;
        }
        find( chars, index + 1, ans, path );
        find( chars, index + 1, ans, path + chars[index] );
    }
}
