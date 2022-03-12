package com.example.algorithm_basic_zx.tree;

import java.util.*;

public class A016_TopoSort {
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

    public static class LevelInfoComparator implements Comparator<LevelInfo> {

        @Override
        public int compare(LevelInfo o1, LevelInfo o2) {
            return o2.level - o1.level;
        }
    }

    public static String topoSort(TreeNode node) {
        if (node == null) {
            return null;
        }
//        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        PriorityQueue<LevelInfo> levelMap = new PriorityQueue<>( new LevelInfoComparator() );
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( node );
        int level = 1;
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            size--;
            levelMap.offer( new LevelInfo( poll.val, level ) );
            if (poll.left != null) {
                queue.offer( poll.left );
            }
            if (poll.right != null) {
                queue.offer( poll.right );
            }
            if (size == 0) {
                level++;
                size = queue.size();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!levelMap.isEmpty()) {
            LevelInfo poll = levelMap.poll();
            sb.append( "(" + poll.val + "level:" + poll.level + ")" );
        }
        return sb.toString();
    }

    public static class LevelInfo {
        public int val;
        public int level;

        public LevelInfo(int val, int level) {
            this.val = val;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode( 1 );
        TreeNode t1 = new TreeNode( 2 );
        TreeNode t2 = new TreeNode( 3 );
        root.left = t1;
        root.right = t2;
        TreeNode t4 = new TreeNode( 4 );
        TreeNode t5 = new TreeNode( 5 );
        TreeNode t6 = new TreeNode( 6 );
        TreeNode t7 = new TreeNode( 7 );
        TreeNode t8 = new TreeNode( 8 );
        t1.left = t4;
        t1.right = t5;

        t2.left = t6;
        t2.right = t7;
        t4.right = t8;

        System.out.println( topoSort( root ) );
        // 8 4 5 6 7 2 3 1

    }
}
