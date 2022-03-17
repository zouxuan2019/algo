package com.example.algorithm_basic_zx.other;

public class T013_PowerOfThree {

    public static boolean isPowerOfThree(int n) {
        // 3^19 = 1162261467
        return n > 0 && (1162261467 % n == 0);
    }
}
