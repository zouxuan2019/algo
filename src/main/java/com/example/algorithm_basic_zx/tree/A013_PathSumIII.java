package com.example.algorithm_basic_zx.tree;

import java.util.HashMap;

//https://leetcode.com/problems/path-sum-iii/
public class A013_PathSumIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }

    public static int pathSum(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put( 0, 1 );
        return process( root, 0, targetSum, preSumMap );
    }

    public static int process(TreeNode node, int allSum, int targetSum, HashMap<Integer, Integer> preSumMap) {
        if (node == null) {
            return 0;
        }
        allSum = node.val + allSum;
        int target = allSum - targetSum;
        int ans = 0;
        if (preSumMap.containsKey( target )) {
            ans = preSumMap.get( target );
        }
        if (preSumMap.containsKey( allSum )) {
            preSumMap.put( allSum, preSumMap.get( allSum ) + 1 );
        } else {
            preSumMap.put( allSum, 1 );
        }
        int left = process( node.left, allSum, targetSum, preSumMap );
        int right = process( node.right, allSum, targetSum, preSumMap );

        ans += left;
        ans += right;
        if (preSumMap.get( allSum ) == 1) {
            preSumMap.remove( allSum );
        } else {
            preSumMap.put( allSum, preSumMap.get( allSum ) - 1 );
        }

        return ans;
    }
}
