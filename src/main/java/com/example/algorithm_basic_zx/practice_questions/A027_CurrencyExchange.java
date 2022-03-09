//package com.example.algorithm_basic_zx.practice_questions;
//
//import java.util.*;
//
//public class A027_CurrencyExchange {
//    public static void main(String[] args) {
//        convert( "EUR", 100, "INR" );
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
//    static double convert(String sourceCurrency, double amount, String destCurrency) {
//        MyGraph myGraph = generateMyGraph();
//        MyNode sourceNode = myGraph.nodes.get( sourceCurrency );
//        HashMap<MyNode, List<MyNode>> myNodeIntegerHashMap = dijkstra2( sourceNode, 6 );
//        for (Map.Entry<MyNode, List<MyNode>> a : myNodeIntegerHashMap.entrySet()) {
//            System.out.println( a.getKey().value );
//            for (MyNode n : a.getValue()) {
//                System.out.println( n.toString() );
//            }
//            System.out.println( "___________________" );
//        }
//        return 0;
//    }
//
//    public static HashMap<MyNode, List<MyNode>> dijkstra2(MyNode head, int size) {
//        MyNodeHeap nodeHeap = new MyNodeHeap( size );
//        nodeHeap.addOrUpdateOrIgnore( head,head, 0 );
//        HashMap<MyNode, List<MyNode>> result = new HashMap<>();
//        while (!nodeHeap.isEmpty()) {
//            MyNodeRecord record = nodeHeap.pop();
//            MyNode cur = record.node;
////            int distance = record.distance;
//            List<MyNode> myNodes = record.path;
//            for (MyEdge edge : cur.edges) {
//                nodeHeap.addOrUpdateOrIgnore(edge.from, edge.to, edge.weight + record.distance );
//            }
//            result.put( cur, myNodes );
//        }
//        return result;
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
//
//    public static class MyNodeRecord {
//        public MyNode node;
//        public int distance;
//        public List<MyNode> path;
//
//        public MyNodeRecord(MyNode node, int distance, List<MyNode> path) {
//            this.node = node;
//            this.distance = distance;
//            this.path = path;
//        }
//    }
//
//    public static class MyNodeHeap {
//        private MyNode[] nodes; // 实际的堆结构
//        // key 某一个node， value 上面堆中的位置
//        private HashMap<MyNode, Integer> heapIndexMap;
//        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
//        private HashMap<MyNode, Integer> distanceMap;
//        private HashMap<MyNode, List<MyNode>> pathMap;
//        private int size; // 堆上有多少个点
//
//        public MyNodeHeap(int size) {
//            nodes = new MyNode[size];
//            heapIndexMap = new HashMap<>();
//            distanceMap = new HashMap<>();
//            pathMap = new HashMap<>();
//            size = 0;
//        }
//
//        public MyNodeRecord pop() {
//            MyNodeRecord nodeRecord = new MyNodeRecord( nodes[0], distanceMap.get( nodes[0] ), pathMap.get( nodes[0] ) );
//            swap( 0, size - 1 );
//            heapIndexMap.put( nodes[size - 1], -1 );
//            distanceMap.remove( nodes[size - 1] );
//            pathMap.remove( nodes[size - 1] );
//            // free C++同学还要把原本堆顶节点析构，对java同学不必
//            nodes[size - 1] = null;
//            heapify( 0, --size );
//            return nodeRecord;
//        }
//
//        public boolean isEmpty() {
//            return size == 0;
//        }
//
//        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
//        // 判断要不要更新，如果需要的话，就更新
//        public void addOrUpdateOrIgnore(MyNode from, MyNode node, int distance) {
//            if (inHeap( node )) {
//                distanceMap.put( node, Math.min( distanceMap.get( node ), distance ) );
//                List<MyNode> myNodes = pathMap.get( node );
//                myNodes.add( from );
//                pathMap.put( node, myNodes );
//                insertHeapify( node, heapIndexMap.get( node ) );
//            }
//            if (!isEntered( node )) {
//                nodes[size] = node;
//                heapIndexMap.put( node, size );
//                distanceMap.put( node, distance );
//                List<MyNode> myNodes = new ArrayList<>();
//                myNodes.add( from );
//                pathMap.put( node, myNodes );
//                insertHeapify( node, size++ );
//            }
//        }
//
//        private void insertHeapify(MyNode node, int index) {
//            while (distanceMap.get( nodes[index] ) < distanceMap.get( nodes[(index - 1) / 2] )) {
//                swap( index, (index - 1) / 2 );
//                index = (index - 1) / 2;
//            }
//        }
//
//        private void heapify(int index, int size) {
//            int left = index * 2 + 1;
//            while (left < size) {
//                int smallest = left + 1 < size && distanceMap.get( nodes[left + 1] ) < distanceMap.get( nodes[left] )
//                        ? left + 1
//                        : left;
//                smallest = distanceMap.get( nodes[smallest] ) < distanceMap.get( nodes[index] ) ? smallest : index;
//                if (smallest == index) {
//                    break;
//                }
//                swap( smallest, index );
//                index = smallest;
//                left = index * 2 + 1;
//            }
//        }
//
//        private boolean isEntered(MyNode node) {
//            return heapIndexMap.containsKey( node );
//        }
//
//        private boolean inHeap(MyNode node) {
//            return isEntered( node ) && heapIndexMap.get( node ) != -1;
//        }
//
//        private void swap(int index1, int index2) {
//            heapIndexMap.put( nodes[index1], index2 );
//            heapIndexMap.put( nodes[index2], index1 );
//            MyNode tmp = nodes[index1];
//            nodes[index1] = nodes[index2];
//            nodes[index2] = tmp;
//        }
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
//}
