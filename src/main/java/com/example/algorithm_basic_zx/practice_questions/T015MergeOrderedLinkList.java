package com.example.algorithm_basic_zx.practice_questions;

public class T015MergeOrderedLinkList {
    // 1->2->4->5
    // 3->5->7
    // 1->2->4->5->7
    public static void main(String[] args) {
        LinkNode ln1 = new LinkNode( 1 );
        LinkNode ln2 = new LinkNode( 2 );
        LinkNode ln3 = new LinkNode( 4 );
        LinkNode ln4 = new LinkNode( 5 );
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;

        LinkNode sn1 = new LinkNode( 3 );
        LinkNode sn2 = new LinkNode( 5 );
        LinkNode sn3 = new LinkNode( 7 );
        sn1.next = sn2;
        sn2.next = sn3;

        printLinkList( ln1 );
        printLinkList( sn1 );
        LinkNode ln = merge2( ln1, sn1 );
        printLinkList( ln );
    }


    private static LinkNode merge2(LinkNode ln1, LinkNode ln2) {
        if (ln1 == null || ln2 == null) {
            return ln1 == null ? ln2 : ln1;
        }
        LinkNode head = ln1.value < ln2.value ? ln1 : ln2;
        LinkNode cur1 = head.next;
        LinkNode cur2 = head == ln1 ? ln2 : ln1;
        LinkNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }


    private static LinkNode merge(LinkNode ln1, LinkNode ln2) {
        if (ln1 == null || ln2 == null) {
            return ln1 == null ? ln2 : ln1;
        }
        LinkNode head = ln1.value < ln2.value ? ln1 : ln2;
        LinkNode cur2 = head == ln1 ? ln2 : ln1;
        LinkNode cur = head.next;
        LinkNode pre = head;
        while (cur != null && cur2 != null) {
            if (cur.value <= cur2.value) {
                pre.next = cur;
                cur = cur.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur != null ? cur : cur2;
        return head;
    }

    private static void printLinkList(LinkNode head) {
        System.out.println( "================" );
        while (head != null) {
            System.out.print( head.value + " -> " );
            head = head.next;
        }
        System.out.println( "================" );
    }

    public static class LinkNode {
        public LinkNode(int value) {
            this.value = value;
        }

        int value;
        LinkNode next;
    }
}
