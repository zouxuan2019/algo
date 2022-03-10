package com.example.algorithm_basic_zx.tree;

//https://leetcode.com/problems/house-robber-iii/
public class A012_HouseRobberIII {
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

    public static class Info {
        public int no;
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process( root );
        return Math.max( info.no, info.yes );
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info( 0, 0 );
        }
        Info left = process( x.left );
        Info right = process( x.right );
        int no = Math.max( left.no, left.yes ) + Math.max( right.no, right.yes );
        int yes = x.val + left.no + right.no;
        return new Info( no, yes );
    }

}
