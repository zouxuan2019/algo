package com.example.algorithm_basic_zx.tree;

import java.util.Arrays;

public class T002FindOriginalArray {
    public static void main(String[] args) {
        int[] arr = {6, 3, 0, 2, 2, 0, 0};
        int[] ans = new int[arr.length];
        T001_SizeBalanceTreeMap.SizeBalancedTreeMap<Integer, Integer> treeMap =
                new T001_SizeBalanceTreeMap.SizeBalancedTreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            treeMap.put( i + 1, i + 1 );
        }
        for (int i = 0; i < arr.length; i++) {
            int rank = treeMap.size() - arr[i];
            ans[i] = treeMap.getIndexKey( rank - 1 );
            treeMap.remove( ans[i] );
        }
        System.out.println( Arrays.toString( ans ) );
    }
}
