package com.example.algorithm_basic_zx.practice_questions;

import java.util.ArrayList;
import java.util.List;

public class A021_CombinationEqualToSum {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = combinationSum( arr, target );
        for (List<Integer> list : lists) {
            System.out.println( list.toString() );
        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> selected = new ArrayList<>();
        ways( target, candidates, selected, result );
        return result;
    }


    public static int ways(int rest, int[] candicates, List<Integer> selected, List<List<Integer>> finalList) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        for (int num : candicates) {
            selected.add( num );
            ans += ways( rest - num, candicates, selected, finalList );
        }
        if (ans == 1) {
            finalList.add( selected );
        } else {
            selected.clear();
        }
        return ans;
    }
}
