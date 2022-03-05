package com.example.algorithm_basic_zx.link_stack;

public class T008_ReverseList {

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
        Node rh = reverseLinkedList( head );
        while (rh.next != null) {
            System.out.println( rh.value );
            rh = rh.next;
        }
        System.out.println( rh.value );
    }

    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
