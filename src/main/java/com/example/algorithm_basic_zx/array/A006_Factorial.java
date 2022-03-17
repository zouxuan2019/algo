package com.example.algorithm_basic_zx.array;

public class A006_Factorial {
    public static void main(String[] args) {
        System.out.println( factorial( 3 ) );
        System.out.println( factorial( 4 ) );
        System.out.println( factorial( 20 ) );
        System.out.println( (1 << 20) );
        System.out.println( (1 << 10) );
    }

    public static long factorial(int x) {
        long pre = 1;
        for (int i = 1; i < x + 1; i++) {
            pre = i * pre;
        }
        return pre;
    }
}
