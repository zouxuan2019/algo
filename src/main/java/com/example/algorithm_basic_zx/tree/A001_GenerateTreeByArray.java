package com.example.algorithm_basic_zx.tree;
//https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class A001_GenerateTreeByArray {

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

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        int[] nearBig = generateNearBig2( preorder );
        return process2( preorder, 0, preorder.length - 1, nearBig );
    }

    public int[] generateNearBig2(int[] pre) {
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

    public TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
        if (R < L) {
            return null;
        }
        int firstBig = nearBig[L] == -1 ? R + 1 : nearBig[L];
        TreeNode treeNode = new TreeNode( pre[L] );
        treeNode.left = process2( pre, L + 1, firstBig - 1, nearBig );
        treeNode.right = process2( pre, firstBig, R, nearBig );
        return treeNode;
    }

}
