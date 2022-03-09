package com.example.algorithm_basic_zx.practice_questions;

import java.util.HashMap;

public class A49_ReceiveAndPrintOrderLine {
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            this.headMap = new HashMap<>();
            this.tailMap = new HashMap<>();
            this.waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node node = new Node( info );
            headMap.put( num, node );
            tailMap.put( num, node );
            if (tailMap.containsKey( num - 1 )) {
                Node tail = tailMap.get( num - 1 );
                tail.next = node;
                tailMap.remove( num - 1 );
            }
            if (headMap.containsKey( num + 1 )) {
                node.next = headMap.get( num + 1 );
                headMap.remove( num + 1 );
                tailMap.remove( num );
            }
            if (num == waitPoint) {
                print();
            }
        }

        private void print() {
            Node head = headMap.get( waitPoint );
            int headPoint = waitPoint;
            while (head != null) {
                System.out.println( head.info );
                head = head.next;
                waitPoint++;
            }
            headMap.remove( headPoint );
            tailMap.remove( waitPoint - 1 );
        }
    }


    public static void main(String[] args) {
// MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println( "这是2来到的时候" );
        box.receive( 2, "B" ); // - 2"
        System.out.println( "这是1来到的时候" );
        box.receive( 1, "A" ); // 1 2 -> print, trigger is 1
        box.receive( 4, "D" ); // - 4
        box.receive( 5, "E" ); // - 4 5
        box.receive( 7, "G" ); // - 4 5 - 7
        box.receive( 8, "H" ); // - 4 5 - 7 8
        box.receive( 6, "F" ); // - 4 5 6 7 8
        box.receive( 3, "C" ); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive( 9, "I" ); // 9 -> print, trigger is 9
        box.receive( 10, "J" ); // 10 -> print, trigger is 10
        box.receive( 12, "L" ); // - 12
        box.receive( 13, "M" ); // - 12 13
        box.receive( 11, "K" ); // 11 12 13 -> print, trigger is 11
    }
}
