package com.example.algorithm_basic_zx.link_stack;

import java.util.Stack;

public class T007_MinStack {
    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push( 3 );
        System.out.println( stack1.getMin() );
        stack1.push( 4 );
        System.out.println( stack1.getMin() );
        stack1.push( 1 );
        System.out.println( stack1.getMin() );
        System.out.println( stack1.pop() );
        System.out.println( stack1.getMin() );

        System.out.println( "=============" );

        MyStack2 stack2 = new MyStack2();
        stack2.push( 3 );
        System.out.println( stack2.getMin() );
        stack2.push( 4 );
        System.out.println( stack2.getMin() );
        stack2.push( 1 );
        System.out.println( stack2.getMin() );
        System.out.println( stack2.pop() );
        System.out.println( stack2.getMin() );
    }

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int num) {
            stackData.push( num );
            if (stackMin.isEmpty()) {
                stackMin.push( num );
            } else {
                Integer peek = stackMin.peek();
                stackMin.push( (peek < num) ? peek : num );
            }
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException( "Your stack is empty" );
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getMin() {
            if (!stackMin.isEmpty()) {
                return stackMin.peek();
            } else {
                throw new RuntimeException( "Your stack is empty" );
            }
        }
    }

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getMin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }
}
