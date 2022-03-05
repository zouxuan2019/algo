package com.example.algorithm_basic_zx.link_stack;

import java.util.Stack;

public class T0000_InverseStack {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push( "a" );
        s.push( "b" );
        s.push( "c" );

        reverse( s );
        while (!s.isEmpty()) {
            System.out.println( s.pop() );
        }
    }

    private static void reverse(Stack<String> s) {
        if (s.size() < 2) {
            return;
        }
        String i = f( s );// c, b, a
        reverse( s );
        s.push( i );
    }

    private static String f(Stack<String> s) {
        String result = s.pop();
        if (s.isEmpty()) {
            return result;
        }
        String last = f( s );
        s.push( result );
        return last;

    }
}
