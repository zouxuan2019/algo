package com.example.algorithm_basic_zx.link_stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class T026_QueueToStack {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.add( 1 );
        myStack.add( 2 );
        myStack.add( 3 );
        System.out.println( myStack.poll() );
        System.out.println( myStack.poll() );
        System.out.println( myStack.peek() );
        System.out.println( myStack.peek() );
        myStack.add( 4 );
        System.out.println( myStack.poll() );
        System.out.println( myStack.poll() );
        System.out.println( myStack.peek() );
        System.out.println( myStack.peek() );
        myStack.add( 5 );
        myStack.add( 6);
        myStack.add( 7 );
        myStack.add( 8 );
        myStack.add( 9 );
        myStack.add( 10 );
        myStack.add( 11 );
        System.out.println( myStack.add( 12 ) );
        System.out.println( myStack.add( 13 ) );
        System.out.println( myStack.add( 14 ) );
        System.out.println( myStack.add( 15 ) );
        System.out.println( myStack.add( 16 ) );
    }

    private static class MyStack<T> {
        private Queue<T> leftQ = new ArrayBlockingQueue( 10 );
        private Queue<T> rightQ = new ArrayBlockingQueue( 10 );

        public boolean add(T v) {
            if (leftQ.size() == 10 || rightQ.size() == 10) {
                return false;
            }
            if (leftQ.isEmpty() && rightQ.isEmpty()) {
                return leftQ.add( v );
            } else if (leftQ.isEmpty()) {
                return rightQ.add( v );
            } else {
                return leftQ.add( v );
            }
        }

        public T peek() {
            return (leftQ.isEmpty()) ? move( rightQ, leftQ, true ) : move( leftQ, rightQ, true );
        }

        public T poll() {
            return (leftQ.isEmpty()) ? move( rightQ, leftQ, false ) : move( leftQ, rightQ, false );
        }

        public T move(Queue<T> qFull, Queue<T> qEmpty, boolean isPeek) {
            if (qFull.isEmpty()) {
                return null;
            }
            while (qFull.size() != 1) {
                qEmpty.add( qFull.poll() );
            }
            T last = qFull.poll();
            if (isPeek) {
                qEmpty.add( last );
            }
            return last;
        }
    }
}
