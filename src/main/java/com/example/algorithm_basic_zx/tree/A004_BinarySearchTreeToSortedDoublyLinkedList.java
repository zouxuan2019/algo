package com.example.algorithm_basic_zx.tree;

public class A004_BinarySearchTreeToSortedDoublyLinkedList {
    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static void main(String[] args) {

        TreeNode ll = new TreeNode( 1 );
        TreeNode r = new TreeNode( 5 );
        TreeNode lr = new TreeNode( 3 );

        TreeNode l = new TreeNode( 2 );
        l.left = ll;
        l.right = lr;
        TreeNode treeNode = new TreeNode( 4 );
        treeNode.right = r;
        treeNode.left = l;

        TreeNode linklist = treeToDoublyList( treeNode );
        TreeNode head = linklist;
        int size = 5;
        while (head != null && size-- > 0) {
            System.out.print( head.val + " " );
            head = head.right;
        }
        System.out.println( "-----------------" );
        TreeNode head2 = linklist;
        size = 5;
        while (head2 != null && size-- > 0) {
            System.out.print( head2.val + " " );
            head2 = head2.left;
        }
    }

    public static TreeNode treeToDoublyList(TreeNode root) {
        Info process = process( root );
        process.start.left = process.end;
        process.end.right = process.start;
        return process.start;
    }

    public static class Info {
        TreeNode start;
        TreeNode end;

        public Info(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info( null, null );
        }
        Info lInfo = process( node.left );
        Info rInfo = process( node.right );
        if (lInfo.end != null) {
            lInfo.end.right = node;
        }
        node.left = lInfo.end;
        node.right = rInfo.start;
        if (rInfo.start != null) {
            rInfo.start.left = node;
        }
        return new Info( lInfo.start != null ? lInfo.start : node, rInfo.end != null ? rInfo.end : node );
    }

}
