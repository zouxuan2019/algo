package com.example.algorithm_basic_zx.singleton;

public class Singleton02 {
    private volatile static Singleton02 INSTANCE;

    private Singleton02() {

    }

    public static Singleton02 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton02.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton02();
                }
            }
        }
        return INSTANCE;
    }
}
