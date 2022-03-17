package com.example.algorithm_basic_zx.practice_questions;

public class T016_BitForAddSubMulDevide {
    public static void main(String[] args) {
//        long num = 0;
//        long d = num / 64;
//        System.out.println( (num / 64 == num >> 6) );
//        System.out.println( (num % 128 == (num & 127)) );
        binary( 4L );
        binary( -4L );
        binary( 20L );
        int a = 46;
        int b = 20;
        long result = (a ^ b) + ((a & b) << 1);
        System.out.println( result );
        System.out.println( add( a, b ) );
        System.out.println( sub( a, b ) );
//        System.out.println( "-3*4 = " + mul( -3, 4 ) );
    }


    public static int devide(int a, int b) {
        return 0;
    }

    public static int sub(int a, int b) {
        return add( a, add( ~b, 1 ) );
    }

//    public static int mul(int a, int b) {
//        int res = 0;
//        while (b != 0) {
//            if ((b & 1) != 0) {
//                res = add( res, a );
//            }
//            a = a << 1;
//            b = b >>> 1;
//        }
//        return res;
//    }
//
//    public static int add(int a, int b) {
//        int sum = a;
//        while (b != 0) {
//            sum = a ^ b;
//            b = (a & b) << 1;
//            a = sum;
//        }
//        return sum;
//    }

    private static void binary(long n) {
        for (long i = 63; i >= 0; i--) {
            System.out.print( ((n & (1L << i)) != 0 ? 1 : 0) );
        }
        System.out.println( "" );
    }

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static int negNum(int n) {
        return add(~n, 1);
    }

    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }
}
