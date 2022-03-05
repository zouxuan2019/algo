package com.example.algorithm_basic_zx.link_stack;

import java.util.Stack;

public class T025_StackToQueue {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue();
        queue.add( 1 );
        queue.add( 2 );
        queue.add( 3 );
        System.out.println( queue.poll() );
        System.out.println( queue.poll() );
        queue.add( 4 );
        System.out.println( queue.peek() );
        System.out.println( queue.peek() );
        System.out.println( queue.poll() );
        System.out.println( queue.poll() );
        System.out.println( queue.poll() );

    }

    private static class MyQueue<T> {
        private Stack<T> pushStack = new Stack<>();
        private Stack<T> popStack = new Stack<>();

        public boolean add(T v) {
            pushToPop();
            return pushStack.add( v );
        }

        public T poll() {
            pushToPop();
            return popStack.isEmpty() ? null : popStack.pop();
        }

        public T peek() {
            pushToPop();
            return popStack.isEmpty() ? null : popStack.peek();
        }

        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    T pop = pushStack.pop();
                    popStack.add( pop );
                }
            }
        }
    }
}
