package com.example.algorithm_basic_zx.tree;

// https://leetcode.com/problems/longest-univalue-path/
public class A009_LongestUnivaluePath {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode( 1 );
        TreeNode n2 = new TreeNode( 1 );
        TreeNode n3 = new TreeNode( 1 );
        TreeNode n4 = new TreeNode( 1 );
        TreeNode n5 = new TreeNode( 1 );
        TreeNode n6 = new TreeNode( 1 );
        TreeNode n7 = new TreeNode( 1 );
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        System.out.println( longestUnivaluePath( n1 ) );
    }

    //Definition for a binary tree node.
    public static class TreeNode {
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

    public static class Info {
        public int containParLen;
        public int max;

        public Info(int parentValueLen, int noParentValueLen) {
            this.containParLen = parentValueLen;
            this.max = noParentValueLen;
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process( root ).max - 1;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info( 0, 0 );
        }
        TreeNode l = node.left;
        TreeNode r = node.right;
        Info leftInfo = process( l );
        Info rightInfo = process( r );
        int len = 1;
        if (l != null && l.val == node.val) {
            len += leftInfo.containParLen;
        }
        if (r != null && r.val == node.val) {
            len = Math.max( len, rightInfo.containParLen + 1 );
        }

        int max = Math.max( len, Math.max( leftInfo.max, rightInfo.max ) );
        if (l != null && l.val == node.val && r != null && r.val == node.val) {
            max = Math.max( max, (leftInfo.containParLen + rightInfo.containParLen + 1) );
        }

        return new Info( len, max );
    }
}
