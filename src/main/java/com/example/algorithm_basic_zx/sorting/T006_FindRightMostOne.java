package com.example.algorithm_basic_zx.sorting;

public class T006_FindRightMostOne {
    public static void main(String[] args) {
        int num = 18;
        int rm = num & (-num);

        System.out.println( rm );
        printBinary( num );
        printBinary( rm );
    }

    private static void printBinary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            int a = (num & (1 << i));
            sb.append( a != 0 ? "1" : "0" );
        }
        System.out.println( sb );
    }
}
