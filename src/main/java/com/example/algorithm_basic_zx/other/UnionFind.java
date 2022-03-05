package com.example.algorithm_basic_zx.other;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    public static class Node<V> {
        private V v;

        public Node(V v) {
            this.v = v;
        }
    }


    public static class UnionSet<V> {
        private HashMap<V, Node> nodes = new HashMap<>();

        private HashMap<Node, Node> parent = new HashMap<>();

        private HashMap<Node, Integer> sizeMap = new HashMap<>();

        private int maxGroup = 0;

        public int getMaxGroup() {
            return maxGroup;
        }

        public UnionSet(List<V> vList) {
            if (vList == null) {
                return;
            }

            for (V v : vList) {
                Node<V> e = new Node<>( v );
                nodes.put( v, e );
                parent.put( e, e );
                sizeMap.put( e, 1 );
            }
            maxGroup = 1;
        }

        public boolean isSameSet(V a, V b) {
            return findFather( nodes.get( a ) ) == findFather( nodes.get( b ) );
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather( nodes.get( a ) );
            Node<V> bHead = findFather( nodes.get( b ) );
            if (aHead != bHead) {
                int sizeA = sizeMap.get( aHead );
                int sizeB = sizeMap.get( bHead );
                Node<V> big = sizeA > sizeB ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parent.put( small, big );
                sizeMap.put( big, sizeA + sizeB );
                sizeMap.remove( small );
                maxGroup = Math.max( maxGroup, sizeA + sizeB );
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (parent.get( cur ) != cur) {
                path.push( cur );
                cur = parent.get( cur );
            }
            while (!path.isEmpty()) {
                parent.put( path.pop(), cur );
            }
            return cur;
        }
    }
}
