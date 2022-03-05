package com.example.algorithm_basic_zx.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class T001_BFS {
    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {4, 3, 4}, {7, 1, 2}, {7, 1, 5}, {7, 5, 3}, {7, 4, 5}};
        Graph graph = GraphGenerator.createGraph( matrix );
        Node node = graph.nodes.get( 2 );
        bfs( node );

    }

    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add( start );
        set.add( start );
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println( cur.value );
            for (Node next : cur.nexts) {
                if (!set.contains( next )) {
                    set.add( next );
                    queue.add( next );
                }
            }
        }
    }
}
