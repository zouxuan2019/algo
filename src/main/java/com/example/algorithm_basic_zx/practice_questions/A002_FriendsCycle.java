package com.example.algorithm_basic_zx.practice_questions;

import com.example.algorithm_basic_zx.other.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class A002_FriendsCycle {
    public static void main(String[] args) {
        int[][] queries = {{1, 2}, {3, 4}, {1, 4}, {6, 7}, {8, 9}, {10, 11}, {6, 10}, {8, 11}, {11, 21}};
        int[] result = groupFriends( queries );
        int[] result2 = friendsCircle( queries );
        System.out.println( Arrays.toString( result ) );
        System.out.println( Arrays.toString( result2 ) );
    }

    public static int[] friendsCircle(int[][] queries) {
        int len = queries.length;
        int[] size = new int[len];
        HashSet<Integer> distinct = new HashSet<>();
        for (int i = 0; i < len; i++) {
            distinct.add( queries[i][0] );
            distinct.add( queries[i][1] );
        }
        UnionFind.UnionSet unionSet = new UnionFind.UnionSet( Arrays.asList( distinct.toArray() ) );
        for (int i = 0; i < len; i++) {
            unionSet.union( queries[i][0], queries[i][1] );
            size[i] = unionSet.getMaxGroup();
        }
        return size;
    }


    public static int[] groupFriends(int[][] queries) {
        int q = queries.length;
        int[] result = new int[q];
        List<HashSet<Integer>> setAll = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int i1 = queries[i][0];
            int i2 = queries[i][1];
            HashSet<Integer> set1 = null;
            HashSet<Integer> set2 = null;
            for (HashSet<Integer> set : setAll) {
                if (set.contains( i1 )) {
                    set1 = set;
                }
                if (set.contains( i2 )) {
                    set2 = set;
                }
            }
            if (set1 == null && set2 == null) {
                HashSet<Integer> newSet = new HashSet<>();
                newSet.add( i1 );
                newSet.add( i2 );
                setAll.add( newSet );
                result[i] = Math.max( 2, i > 1 ? result[i - 1] : 0 );
                continue;
            }
            if (set1 != null && set2 != null && set1 != set2) {
                HashSet<Integer> biggerSet = new HashSet<>();
                biggerSet.addAll( set1 );
                biggerSet.addAll( set2 );
                setAll.remove( set1 );
                setAll.remove( set2 );
                setAll.add( biggerSet );
                result[i] = biggerSet.size();
                continue;
            }
            if (set2 != null && set1 == set2) {
                result[i] = result[i - 1];
                continue;
            }
            if (set1 != null) {
                set1.add( i2 );
                result[i] = Math.max( set1.size(), result[i - 1] );
                continue;
            }
            set2.add( i1 );
            result[i] = Math.max( set2.size(), result[i - 1] );

        }
        return result;
    }
}
