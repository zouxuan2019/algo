package com.example.algorithm_basic_zx.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class T003_TopologicalOrderBFS {
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            indegreeMap.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next, indegreeMap.get(next) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (DirectedGraphNode cur : indegreeMap.keySet()) {
            if (indegreeMap.get(cur) == 0) {
                zeroQueue.add(cur);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode cur = zeroQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    zeroQueue.offer(next);
                }
            }
        }
        return ans;
    }
}
