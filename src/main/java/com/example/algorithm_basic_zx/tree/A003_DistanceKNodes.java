package com.example.algorithm_basic_zx.tree;

import java.util.*;

//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
public class A003_DistanceKNodes {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        HashMap<TreeNode, TreeNode> parentMap = generateParentMap( root );
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();
        queue.offer( target );
        visited.add( target );

        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
                if (currentLevel == k) {
                    ans.add( poll.val );
                }
                if (poll.left != null && !visited.contains( poll.left )) {
                    queue.offer( poll.left );
                    visited.add( poll.left );

                }
                if (poll.right != null && !visited.contains( poll.right )) {
                    queue.offer( poll.right );
                    visited.add( poll.right );
                }
                TreeNode parent = parentMap.get( poll );
                if (parent != null && !visited.contains( parent )) {
                    queue.offer( parent );
                    visited.add( parent );
                }
            }
            currentLevel++;
            if (currentLevel > k) {
                break;
            }
        }
        return ans;

    }

    public static HashMap<TreeNode, TreeNode> generateParentMap(TreeNode root) {
        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        map.put( root, null );
        process( root, map );
        return map;
    }

    public static void process(TreeNode cur, HashMap<TreeNode, TreeNode> map) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            map.put( cur.left, cur );
            process( cur.left, map );
        }
        if (cur.right != null) {
            map.put( cur.right, cur );
            process( cur.right, map );
        }
    }


    public static void main(String[] args) {
        TreeNode n0 = new TreeNode( 0 );
        TreeNode n1 = new TreeNode( 1 );
        TreeNode n2 = new TreeNode( 2 );
        TreeNode n3 = new TreeNode( 3 );
        TreeNode n4 = new TreeNode( 4 );
        TreeNode n5 = new TreeNode( 5 );
        TreeNode n6 = new TreeNode( 6 );
        TreeNode n7 = new TreeNode( 7 );
        TreeNode n8 = new TreeNode( 8 );

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        TreeNode root = n3;
        TreeNode target = n5;
        int K = 2;

        List<Integer> ans = distanceK( root, target, K );
        for (Integer o1 : ans) {
            System.out.println( o1 );
        }
    }
}
