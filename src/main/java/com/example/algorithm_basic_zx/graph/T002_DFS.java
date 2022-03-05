package com.example.algorithm_basic_zx.graph;

import java.util.HashSet;
import java.util.Stack;

public class T002_DFS {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 3, 4}, {7, 1, 2}, {7, 1, 5}, {7, 5, 3}, {7, 4, 5}};
        Graph graph = GraphGenerator.createGraph( matrix );
        Node node = graph.nodes.get( 5 );
        dfs( node );
    }

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add( node );
        set.add( node );
        System.out.println( node.value );
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains( next )) {
                    stack.push( cur );
                    stack.push( next );
                    set.add( next );
                    System.out.println( next.value );
                    break;
                }
            }
        }
    }
}
