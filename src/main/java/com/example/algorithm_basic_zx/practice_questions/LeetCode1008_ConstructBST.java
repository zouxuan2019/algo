package com.example.algorithm_basic_zx.practice_questions;

import java.util.Stack;

public class LeetCode1008_ConstructBST {
    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode treeNode = bstFromPreorder( preorder );
        System.out.println( treeNode.val );
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        int[] nearBig = generateNearBig2( preorder );
        return process2( preorder, 0, preorder.length - 1, nearBig );
    }

    public static int[] generateNearBig(int[] pre) {
        int[] result = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            result[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pre.length; i++) {
            while (!stack.isEmpty() && pre[i] > pre[stack.peek()]) {
                result[stack.pop()] = i;
            }
            stack.push( i );

        }
        return result;
    }

    public static int[] generateNearBig2(int[] pre) {
        int[] result = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            result[i] = -1;
        }
        int[] stack = new int[pre.length];
        int size = 0;
        for (int i = 0; i < pre.length; i++) {
            while (size > 0 && pre[i] > pre[stack[size - 1]]) {
                result[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return result;
    }

    public static TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
        if (R < L) {
            return null;
        }
        int firstBig = nearBig[L] == -1 ? R + 1 : nearBig[L];
        TreeNode treeNode = new TreeNode( pre[L] );
        treeNode.left = process2( pre, L + 1, firstBig - 1, nearBig );
        treeNode.right = process2( pre, firstBig, R, nearBig );
        return treeNode;
    }

    public static TreeNode process(int[] pre, int L, int R) {
        if (R < L) {
            return null;
        }
        int firstBig = L + 1;
        while (firstBig <= R && pre[firstBig] < pre[L]) {
            firstBig++;
        }
        TreeNode treeNode = new TreeNode( pre[L] );
        treeNode.left = process( pre, L + 1, firstBig - 1 );
        treeNode.right = process( pre, firstBig, R );
        return treeNode;
    }


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
}
