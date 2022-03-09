package com.example.algorithm_basic_zx.practice_questions;

public class A043_MinSwapStepGB {
    // 一个数组中只有两种字符'G'和'B'，
    // 可以让所有的G都放在左侧，所有的B都放在右侧
    // 或者可以让所有的G都放在右侧，所有的B都放在左侧
    // 但是只能在相邻字符之间进行交换操作，请问请问至少需要交换几次，
    // 为了测试
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf( str );
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int testTime = 1000000;
        System.out.println( "测试开始" );
        for (int i = 0; i < testTime; i++) {
            String str = randomString( maxLen );
            int ans1 = minSteps1( str );
            int ans2 = minSteps2( str );
            if (ans1 != ans2) {
                System.out.println( "Oops!" );
            }
        }
        System.out.println( "测试结束" );
    }

    private static int minSteps1(String str) {
        char[] chars = str.toCharArray();
        int gi = 0;

        int minSwap1 = 0;
        int minSwap2 = 0;
        for (int i = 0; i < chars.length; i++) {
            //G to the left
            if (chars[i] == 'G') {
                minSwap1 += i - gi;
                gi++;
            }
        }
        int bi = 0;
        for (int i = 0; i < chars.length; i++) {
            //B to the left
            if (chars[i] == 'B') {
                minSwap2 += i - bi;
                bi++;
            }
        }
        return Math.min( minSwap1, minSwap2 );
    }

    private static int minSteps2(String str) {
        char[] chars = str.toCharArray();
        int gi = 0;
        int bi = 0;
        int minSwap1 = 0;
        int minSwap2 = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                minSwap1 += i - (gi++);
            } else {
                minSwap2 += i - (bi++);
            }
        }
        return Math.min( minSwap1, minSwap2 );
    }
}
