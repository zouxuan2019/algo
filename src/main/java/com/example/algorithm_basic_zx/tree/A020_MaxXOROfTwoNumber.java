package com.example.algorithm_basic_zx.tree;

public class A020_MaxXOROfTwoNumber {
    public static void main(String[] args) {
        int[] arr = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};

        System.out.println( findMaximumXOR( arr ) );
    }

    public static int findMaximumXOR(int[] nums) {
        NumTrie numTrie = new NumTrie();
        for (int j : nums) {
            numTrie.add( j );
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max( max, numTrie.maxXor( num ) );
        }
        return max;
    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                int best = i == 31 ? path : (path ^ 1);
                if (cur.nexts[best] == null) {
                    best = best ^ 1;
                }
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }
}
