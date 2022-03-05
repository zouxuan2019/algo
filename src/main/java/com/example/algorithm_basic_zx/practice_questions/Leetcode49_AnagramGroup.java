package com.example.algorithm_basic_zx.practice_questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Leetcode49_AnagramGroup {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        for (List<String> r : result
        ) {
            System.out.println(r.stream().collect(Collectors.joining(",")));
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String en = EncodeString(strs[i]);
            List<String> val = map.get(en);
            if (val == null) {
                List<String> a = new ArrayList<>();
                a.add(strs[i]);
                map.put(en, a);
            } else {
                val.add(strs[i]);
                map.put(en, val);
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }

    public static String EncodeString(String str) {
        int[] chars = new int[26];
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            int charCount = chars[i];
            if (charCount != 0) {
                sb.append("_").append(charCount).append(Character.toString('a' + i));
            }
        }
        return sb.toString();
    }
}
