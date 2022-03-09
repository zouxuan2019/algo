package com.example.algorithm_basic_zx.tree;

public class A005_CountCompleteBTSize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 请保证head为头的树，是完全二叉树
    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs( head, 1, mostLeftLevel( head, 1 ) );
    }

    public static int bs(Node node, int level, int h) {
        if (level == h) {
            return 1;
        }

        int rightMostLeftHeight = mostLeftLevel( node.right, level + 1 );
        if (rightMostLeftHeight == h) {
            return (1 << (h - level)) + bs( node.right, level + 1, h );
        } else {
            return (1 << (h - level - 1)) + bs( node.left, level + 1, h );
        }

    }

    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node( 1 );
        head.left = new Node( 2 );
        head.right = new Node( 3 );
        head.left.left = new Node( 4 );
        head.left.right = new Node( 5 );
        head.right.left = new Node( 6 );
        System.out.println( nodeNum( head ) );

    }
}
