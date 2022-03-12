package com.example.algorithm_basic_zx.dp;

public class A017_PainHouseII {

    public static void main(String[] args) {
        int[][] costs = {{1, 2, 3}, {1, 4, 6}};
        System.out.println( minCostII( costs ) );
    }

    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int preMin1 = 0;
        int preMin1Color = -1;
        int preMin2 = 0;
        int preMin2Color = -1;

        for (int i = 0; i < costs.length; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin1Color = -1;
            int curMin2 = Integer.MAX_VALUE;
            int curMin2Color = -1;
            for (int j = 0; j < costs[0].length; j++) {
                if (preMin1Color != j) {
                    if ((preMin1 + costs[i][j]) < curMin1) {
                        curMin2 = curMin1;
                        curMin2Color = curMin1Color;
                        curMin1 = preMin1 + costs[i][j];
                        curMin1Color = j;
                    } else if ((preMin1 + costs[i][j]) < curMin2) {
                        curMin2 = preMin1 + costs[i][j];
                        curMin2Color = j;
                    }
                } else if (preMin2Color != j) {
                    if (preMin2 + costs[i][j] < curMin1) {
                        curMin2 = curMin1;
                        curMin2Color = curMin1Color;
                        curMin1 = preMin2 + costs[i][j];
                        curMin1Color = j;
                    } else if ((preMin2 + costs[i][j]) < curMin2) {
                        curMin2 = preMin2 + costs[i][j];
                        curMin2Color = j;
                    }
                }
            }
            preMin1 = curMin1;
            preMin1Color = curMin1Color;
            preMin2 = curMin2;
            preMin2Color = curMin2Color;
        }
        return preMin1;
    }
}
