package com.example.algorithm_basic_zx.tree;

import java.util.HashMap;
import java.util.Map;

//https://www.nowcoder.com/practice/e13bceaca5b14860b83cbcc4912c5d4a
public class A006_BiggestBSTTopologyInTree_ZX {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    public static int maxTopo(Node h, Node n) {
        if (h != null && n != null && isBSTNode(h, n, n.value)) {
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    public static boolean isBSTNode(Node h, Node n, int value) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }
    public static class Record {
        public int leftContribution;
        public int rightContribution;

        public Record(int left, int right) {
            this.leftContribution = left;
            this.rightContribution = right;
        }
    }

    public static int bstTopoSize2(Node head) {
        Map<Node, Record> map = new HashMap<>();
        return posOrder(head, map);
    }

    public static int posOrder(Node cur, Map<Node, Record> map) {
        if (cur == null) {
            return 0;
        }
        int ls = posOrder(cur.left, map);
        int rs = posOrder(cur.right, map);
        modifyMap(cur.left, cur.value, map, true);
        modifyMap(cur.right, cur.value, map, false);
        Record lr = map.get(cur.left);
        Record rr = map.get(cur.right);
        int lbst = lr == null ? 0 : lr.leftContribution + lr.rightContribution + 1;
        int rbst = rr == null ? 0 : rr.leftContribution + rr.rightContribution + 1;
        map.put(cur, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public static int modifyMap(Node cur, int parentValue, Map<Node, Record> map, boolean isLeft) {
        if (cur == null || (!map.containsKey(cur))) {
            return 0;
        }
        Record record = map.get(cur);
        if ((isLeft && cur.value > parentValue) || ((!isLeft) && cur.value < parentValue)) {
            map.remove(cur);
            return record.leftContribution + record.rightContribution + 1;
        } else {
            int minus = modifyMap(isLeft ? cur.right : cur.left, parentValue, map, isLeft);
            if (isLeft) {
                record.rightContribution = record.rightContribution - minus;
            } else {
                record.leftContribution = record.leftContribution - minus;
            }
            map.put(cur, record);
            return minus;
        }
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);
        printTree(head);

//        System.out.println(bstTopoSize1(head));
        System.out.println(bstTopoSize2(head));

    }


}
