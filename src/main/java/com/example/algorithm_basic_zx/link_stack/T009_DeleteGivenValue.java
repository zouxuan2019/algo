package com.example.algorithm_basic_zx.link_stack;

public class T009_DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node( 1 );
        Node node2 = new Node( 2 );
        Node node3 = new Node( 3 );
        Node node4 = new Node( 4 );
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node rh = removeValue( head, 1 );
        while (rh.next != null) {
            System.out.println( rh.value );
            rh = rh.next;
        }
        System.out.println( rh.value );
    }

    private static Node removeValue(Node head, int i) {
        while (head != null) {
            if (head.value != i) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == i) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
