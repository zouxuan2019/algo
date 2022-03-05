package com.example.algorithm_basic_zx.other;

import java.util.*;

public class T001_BestPriceScheduling {
    public static class Vendor {
        int start;
        int end;
        int price;

        public Vendor(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format( "%s %s $%s", this.start, this.end, this.price );
        }
    }

    public static void main(String[] args) {
        Vendor[] vendors = new Vendor[5];
        vendors[0] = new Vendor( 1, 5, 20 );
        vendors[1] = new Vendor( 3, 6, 15 );
        vendors[2] = new Vendor( 2, 8, 25 );
        vendors[3] = new Vendor( 7, 12, 18 );
        vendors[4] = new Vendor( 1, 31, 22 );
        List<Vendor> result = bestScheduling( vendors );
        System.out.println( result );
    }

    public static class VendorComparator implements Comparator<Vendor> {

        @Override
        public int compare(Vendor o1, Vendor o2) {
            int s = o1.start - o2.start;
            if (s != 0) {
                return s;
            }
            return o1.end - o2.end;
        }
    }

    private static List<Vendor> bestScheduling(Vendor[] vendors) {
        int[] price = new int[32];

        for (int i = 0; i < vendors.length; i++) {
            for (int j = vendors[i].start; j <= vendors[i].end; j++) {
                int p = price[j];
                if (p == 0) {
                    price[j] = vendors[i].price;
                } else {
                    price[j] = Math.min( p, vendors[i].price );
                }
            }
        }
        List<Vendor> v = new ArrayList<>();
        int prePrice = 0;
        int start = 1;
        int end = 0;
        for (int i = 1; i < price.length; i++) {
            prePrice = price[start];
            if (price[i] == prePrice) {
                end++;
            } else {
                v.add( new Vendor( start, end, prePrice ) );
                start = i;
                end = start;
            }
        }
        v.add( new Vendor( start, end, prePrice ) );
        return v;

    }
}
