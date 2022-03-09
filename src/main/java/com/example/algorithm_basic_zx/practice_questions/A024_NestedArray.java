package com.example.algorithm_basic_zx.practice_questions;

import org.apache.commons.lang3.ArrayUtils;

public class A024_NestedArray {
    public static void main(String[] args) {
        Object[] arr = new Object[10];
        int[] na1 = {1, 2, 3};
        arr[0] = na1;

        int[] na2 = {3, 4};
        Object[] na3 = new Object[2];
        na3[0] = na2;
        na3[1] = 5;
        arr[1] = na3;
        arr[2] = 6;
        printNested( arr );
    }

    private static void printNested(Object[] arr) {
        if (arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (isArray( arr[i] )) {
                    printNested( convertToObjArray( arr[i] ) );
                } else {
                    System.out.println( arr[i] );
                }
            }
        }
    }

    private static Object[] convertToObjArray(Object obj) {
        if (obj instanceof int[]) {
            int[] arr = ((int[]) obj);
            Object[] res = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                res[i] = arr[i];
            }
            return res;
        }
        return null;
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }
}
