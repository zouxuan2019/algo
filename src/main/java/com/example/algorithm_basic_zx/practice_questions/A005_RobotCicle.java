package com.example.algorithm_basic_zx.practice_questions;

import java.util.HashMap;

public class A005_RobotCicle {
    public static void main(String[] args) {
        String instructions = "GLGLGGLGL";
        System.out.println( isRobotBounded( instructions ) );
    }

    public static boolean isRobotBounded(String instructions) {
        HashMap<String, Cordinator> map = new HashMap<>();
        map.put( "N_G", new Cordinator( 0, 1, "N" ) );
        map.put( "N_L", new Cordinator( 0, 0, "W" ) );
        map.put( "N_R", new Cordinator( 0, 0, "E" ) );

        map.put( "S_G", new Cordinator( 0, -1, "S" ) );
        map.put( "S_L", new Cordinator( 0, 0, "E" ) );
        map.put( "S_R", new Cordinator( 0, 0, "W" ) );

        map.put( "W_G", new Cordinator( -1, 0, "W" ) );
        map.put( "W_L", new Cordinator( 0, 0, "S" ) );
        map.put( "W_R", new Cordinator( 0, 0, "N" ) );

        map.put( "E_G", new Cordinator( 1, 0, "E" ) );
        map.put( "E_L", new Cordinator( 0, 0, "N" ) );
        map.put( "E_R", new Cordinator( 0, 0, "S" ) );

        Cordinator pos = new Cordinator( 0, 0, "N" );
        go( instructions, map, pos, true );
        if (pos.dir.equals( "N" ) && (pos.x != 0 || pos.y != 0)) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            go( instructions, map, pos, false );
        }
        return (pos.x == 0 && pos.y == 0);
    }

    private static void go(String instructions, HashMap<String, Cordinator> map, Cordinator pos, boolean isFirstTry) {
        char[] chars = instructions.toCharArray();
        for (char aChar : chars) {
            String ins = pos.dir + "_" + aChar;
            Cordinator change = map.get( ins );
            pos.dir = change.dir;
            pos.x += change.x;
            pos.y += change.y;
            if (!isFirstTry && pos.x == 0 && pos.y == 0) {
                break;
            }
        }
    }

    public static class Cordinator {
        int x;
        int y;
        String dir;

        public Cordinator(int x, int y, String dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
