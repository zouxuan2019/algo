package com.example.algorithm_basic_zx.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A015_SerializeUnSerializeNaryTree {
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public static class Codec {
        public static String serialize(Node node) {
            if (node == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            sb.append( node.val + "," );
            if (!node.children.isEmpty()) {
                sb.append( "[," );
                for (Node child : node.children) {
                    sb.append( serialize( child ) );
                }
                sb.append( "]," );
            }
            return sb.toString();
        }

        public static Node deserialize(String str) {
            if (str.equals( "#" )) {
                return null;
            }
            String[] splits = str.split( "," );
            Queue<String> queue = new LinkedList<>();
            for (int i = 0; i < splits.length; i++) {
                queue.offer( splits[i] );
            }
            return deserialQueue( queue );
        }


        public static Node deserialQueue(Queue<String> queue) {
            Node cur = new Node( Integer.valueOf( queue.poll() ) );
            cur.children = new ArrayList<>();
            if (!queue.isEmpty() && queue.peek().equals( "[" )) {
                queue.poll();
                while (!queue.peek().equals( "]" )) {
                    Node child = deserialQueue( queue );
                    cur.children.add( child );
                }
                queue.poll();
            }
            return cur;
        }

    }

    public static void main(String[] args) {
        // 如果想跑以下的code，请把Codec类描述和内部所有方法改成static的
        Node a = new Node( 1 );
        Node b = new Node( 2 );
        Node c = new Node( 3 );
        Node d = new Node( 4 );
        Node e = new Node( 5 );
        Node f = new Node( 6 );
        Node g = new Node( 7 );
        a.children.add( b );
        a.children.add( c );
        a.children.add( d );
        b.children.add( e );
        b.children.add( f );
        e.children.add( g );
        String content = Codec.serialize( a );
        System.out.println( content );
        Node head = Codec.deserialize( content );
        System.out.println( content.equals( Codec.serialize( head ) ) );
    }
}
