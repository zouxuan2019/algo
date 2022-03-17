package com.example.algorithm_basic_zx.link_stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class T028_FlattenNestedListIterator {
    // 不要提交这个接口类
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class NestedIntegerImpl {
        public boolean isInteger;
        public Integer val;
        public List<NestedIntegerImpl> list;

        public NestedIntegerImpl(boolean isInteger, Integer val, List<NestedIntegerImpl> list) {
            this.isInteger = isInteger;
            this.val = val;
            this.list = list;
        }
    }

    public static void main(String[] args) {
        List<NestedIntegerImpl> list = new ArrayList<>();
        NestedIntegerImpl c = new NestedIntegerImpl( false, 3, null );
        NestedIntegerImpl d = new NestedIntegerImpl( true, 4, null );
        NestedIntegerImpl b = new NestedIntegerImpl( true, 2, null );
        list.add( c );
        list.add( d );
        list.add( b );

        NestedIntegerImpl e = new NestedIntegerImpl( true, 5, null );
        NestedIntegerImpl a = new NestedIntegerImpl( true, 1, null );
        List<NestedIntegerImpl> clist = new ArrayList<>();
        clist.add( e );
        clist.add( a );
        c.list = clist;

        NestedIntegerImpl head = new NestedIntegerImpl( false, 6, list );
        print( head );//5 1 4 2

    }

    public static void print(NestedIntegerImpl n) {
        if (n.isInteger) {
            System.out.println( n.val );
            return;
        }
        if (n.list != null) {
            for (NestedIntegerImpl i : n.list) {
                print( i );
            }
        }
    }

    public class NestedIterator implements Iterator<Integer> {

        private List<NestedInteger> list;
        private Stack<Integer> stack;
        private boolean used;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = nestedList;
            stack = new Stack<>();
            stack.push( -1 );
            used = true;
            hasNext();
        }

        @Override
        public Integer next() {
            Integer ans = null;
            if (!used) {
                ans = get( list, stack );
                used = true;
                hasNext();
            }
            return ans;
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            if (!used) {
                return true;
            }
            if (findNext( list, stack )) {
                used = false;
            }
            return !used;
        }

        private Integer get(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            Integer ans = null;
            if (!stack.isEmpty()) {
                ans = get( nestedList.get( index ).getList(), stack );
            } else {
                ans = nestedList.get( index ).getInteger();
            }
            stack.push( index );
            return ans;
        }

        private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            if (!stack.isEmpty() && findNext( nestedList.get( index ).getList(), stack )) {
                stack.push( index );
                return true;
            }
            for (int i = index + 1; i < nestedList.size(); i++) {
                if (pickFirst( nestedList.get( i ), i, stack )) {
                    return true;
                }
            }
            return false;
        }

        private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
            if (nested.isInteger()) {
                stack.add( position );
                return true;
            } else {
                List<NestedInteger> actualList = nested.getList();
                for (int i = 0; i < actualList.size(); i++) {
                    if (pickFirst( actualList.get( i ), i, stack )) {
                        stack.add( position );
                        return true;
                    }
                }
            }
            return false;
        }

    }
}
