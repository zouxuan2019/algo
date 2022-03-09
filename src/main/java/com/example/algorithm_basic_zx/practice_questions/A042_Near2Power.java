package com.example.algorithm_basic_zx.practice_questions;

public class A042_Near2Power {
    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static void main(String[] args) {
        System.out.println( near2Power( 7 ) );
        System.out.println( near2Power( 8 ) );
        System.out.println( near2Power( 0 ) );
        System.out.println( near2Power( 1 ) );
    }

    public static final int near2Power(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n + 1);
    }

}
