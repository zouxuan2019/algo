package com.example.algorithm_basic_zx.practice_questions;

public class LeftRightSameTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static class Info {
        int count;
        String str;

        public Info(int count, String str) {
            this.count = count;
            this.str = str;
        }
    }

    public static void main(String[] args) {
        Node head = new Node( 2 );
        Node node1 = new Node( 3 );
        Node node2 = new Node( 3 );
        head.left = node1;
        head.right = node2;
//        System.out.println( serializeInOrder( head ) );
        Node n = randomBinaryTree( 6, 10 );
        Info res = getSameTreeCount2( n );
        int result = getSameTreeCount( n );
        System.out.println( res.count );
        System.out.println( result );

//        System.out.println( serializeInOrder( n ) );
//        System.out.println( res.str );
    }

    public static Info getSameTreeCount2(Node n) {
        if (n == null) {
            return new Info( 0, "null" );
        }
        Info l = getSameTreeCount2( n.left );
        Info r = getSameTreeCount2( n.right );
        Info res = new Info( l.count + r.count + (l.str.equals( r.str ) ? 1 : 0), l.str + n.value + r.str );
        return res;
    }


    public static String serializeInOrder(Node head) {
        if (head == null) {
            return "null";
        }
        String sb = serializeInOrder( head.getLeft() ) +
                head.value +
                serializeInOrder( head.getRight() );
        return sb;
    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node( (int) (Math.random() * maxValue) );
        if (head != null) {
            head.left = randomBinaryTree( restLevel - 1, maxValue );
            head.right = randomBinaryTree( restLevel - 1, maxValue );
        }
        return head;
    }

    public static int getSameTreeCount(Node head) {
        if (head == null) {
            return 0;
        }
        int p1 = isSameTree( head.getLeft(), head.getRight() ) ? 1 : 0;
        int pl = getSameTreeCount( head.getLeft() );
        int pr = getSameTreeCount( head.getRight() );
        return p1 + pl + pr;

    }

    public static boolean isSameTree(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null ^ node2 == null) {
            return false;
        }
        return node1.value == node2.value &&
                isSameTree( node1.getLeft(), node1.getRight() ) &&
                isSameTree( node2.getLeft(), node2.getRight() );
    }
}
