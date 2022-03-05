package com.example.algorithm_basic_zx.singleton;

public class Singleton03 {
    private Singleton03() {
    }

    private static class Singleton03Holder {
        private final static Singleton03 INSTANCE = new Singleton03();
    }

    public static Singleton03 getInstance() {
        return Singleton03Holder.INSTANCE;
    }

}
