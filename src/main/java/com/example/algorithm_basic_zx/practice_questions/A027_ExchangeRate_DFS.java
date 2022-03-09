//package com.example.algorithm_basic_zx.practice_questions;
//
//import java.util.*;
//
//public class A027_ExchangeRate_DFS {
//
//    public static void main(String[] args) {
//        MyGraph myGraph = generateMyGraph();
//        MyNode sourceNode = myGraph.nodes.get( "EUR" );
//        MyNode targetNode = myGraph.nodes.get( "INR" );
//        dfs( sourceNode, targetNode );
//    }
//
//    public static class Currency {
//        String from;
//        String to;
//        double rate;
//
//        public Currency(String from, String to, double rate) {
//            this.from = from;
//            this.to = to;
//            this.rate = rate;
//        }
//    }
//
//    public static class MyGraph {
//        public HashMap<String, MyNode> nodes;
//        public HashSet<MyEdge> edges;
//
//        public MyGraph() {
//            nodes = new HashMap<>();
//            edges = new HashSet<>();
//        }
//    }
//
//    private static List<Currency> generateCurrencyList() {
//        List<Currency> currencyList = new ArrayList<>();
//        Currency c1 = new Currency( "EUR", "USD", 1.2 );
//        Currency c2 = new Currency( "USD", "GBP", 0.75 );
//        Currency c3 = new Currency( "GBP", "AUD", 1.7 );
//        Currency c4 = new Currency( "AUD", "JPY", 90 );
//        Currency c5 = new Currency( "GBP", "JPY", 150 );
//        Currency c6 = new Currency( "JPY", "INR", 0.6 );
//        currencyList.add( c1 );
//        currencyList.add( c2 );
//        currencyList.add( c3 );
//        currencyList.add( c4 );
//        currencyList.add( c5 );
//        currencyList.add( c6 );
//        return currencyList;
//    }
//
//    public static MyGraph generateMyGraph() {
//        List<Currency> currencyList = generateCurrencyList();
//        MyGraph graph = new MyGraph();
//        for (Currency curr : currencyList) {
//            // 拿到每一条边， matrix[i]
//            int weight = 1;
//            String from = curr.from;
//            String to = curr.to;
//            if (!graph.nodes.containsKey( from )) {
//                graph.nodes.put( from, new MyNode( from ) );
//            }
//            if (!graph.nodes.containsKey( to )) {
//                graph.nodes.put( to, new MyNode( to ) );
//            }
//            MyNode fromNode = graph.nodes.get( from );
//            MyNode toNode = graph.nodes.get( to );
//            MyEdge newEdge = new MyEdge( weight, fromNode, toNode );
//            fromNode.nexts.add( toNode );
//            fromNode.out++;
//            toNode.in++;
//            fromNode.edges.add( newEdge );
//        }
//        return graph;
//    }
//
//    static class MyNode {
//        public String value;
//        public int in;
//        public int out;
//        public ArrayList<MyNode> nexts;
//        public ArrayList<MyEdge> edges;
//
//        public MyNode(String value) {
//            this.value = value;
//            in = 0;
//            out = 0;
//            nexts = new ArrayList<>();
//            edges = new ArrayList<>();
//        }
//
//        @Override
//        public String toString() {
//            return "MyNode{" +
//                    "value='" + value + '\'' +
//                    '}';
//        }
//    }
//
//    public static class MyEdge {
//        public int weight;
//        public MyNode from;
//        public MyNode to;
//
//        public MyEdge(int weight, MyNode from, MyNode to) {
//            this.weight = weight;
//            this.from = from;
//            this.to = to;
//        }
//
//    }
//
//    public static List<String> dfs(MyNode from, MyNode to) {
//        if (from == null) {
//            return null;
//        }
//        List<String> result = new ArrayList<>();
//        while (from.nexts != null) {
//
//        }
//        }
//
////        Stack<MyNode> stack = new Stack<>();
////        HashSet<MyNode> set = new HashSet<>();
////        stack.add( from );
////        set.add( from );
////        StringBuilder sb = new StringBuilder( from.value );
////        System.out.println( from.value );
////        while (!stack.isEmpty()) {
////            MyNode cur = stack.pop();
////            for (MyNode next : cur.nexts) {
////                if (!set.contains( next )) {
////                    stack.push( cur );
////                    stack.push( next );
////                    set.add( next );
////                    sb.append( next.value );
////                    System.out.println( next.value );
////                    break;
////                }
////            }
////        }
////        return result;
////    }
//    }
