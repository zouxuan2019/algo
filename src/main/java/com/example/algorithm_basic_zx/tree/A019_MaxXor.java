package com.example.algorithm_basic_zx.tree;

public class A019_MaxXor {
    // O(N^2)
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max( max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1] );
            }
        }
        return max;
    }

    // 前缀树的Node结构
    // nexts[0] -> 0方向的路
    // nexts[1] -> 1方向的路
    // nexts[0] == null 0方向上没路！
    // nexts[0] != null 0方向有路，可以跳下一个节点
    // nexts[1] == null 1方向上没路！
    // nexts[1] != null 1方向有路，可以跳下一个节点
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    // 基于本题，定制前缀树的实现
    public static class NumTrie {
        // 头节点
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = ((newNum >> i) & 1);
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // num和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int num) {
            int ans = 0;
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                int expectBest = i == 31 ? path : (path ^ 1); // sign bit expect same to make number bigger
                if (cur.nexts[expectBest] == null) {
                    expectBest = expectBest ^ 1;
                }
                ans |= ((path ^ expectBest) << i);
                cur = cur.nexts[expectBest];
            }
            return ans;
        }
    }

    // O(N)
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        NumTrie numTrie = new NumTrie();
        numTrie.add( 0 );
        int xor = 0;
        for (int j : arr) {
            xor ^= j;
            max = Math.max( max, numTrie.maxXor( xor ) );
            numTrie.add( xor );
        }

        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print( arr[i] + " " );
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray( maxSize, maxValue );
            int comp = maxXorSubarray1( arr );
            int res = maxXorSubarray2( arr );
            if (res != comp) {
                succeed = false;
                printArray( arr );
                System.out.println( res );
                System.out.println( comp );
                break;
            }
        }
        System.out.println( succeed ? "Nice!" : "Fucking fucked!" );
    }
}
