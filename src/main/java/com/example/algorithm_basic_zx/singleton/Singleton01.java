package com.example.algorithm_basic_zx.singleton;

public class Singleton01 {
    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {
        i++;
    }

    private int i = 10;

    public static Singleton01 getInstance() {
        return INSTANCE;
    }

    public int getI() {
        return i;
    }

    public void setI() {
        i = -1;
    }
}
