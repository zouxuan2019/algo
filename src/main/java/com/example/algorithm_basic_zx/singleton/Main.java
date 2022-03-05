package com.example.algorithm_basic_zx.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Singleton01 singleton01 = Singleton01.getInstance();
//        Singleton02 singleton02 = Singleton02.getInstance();
//        Singleton03 singleton03 = Singleton03.getInstance();
//        Singleton04 singleton04 = Singleton04.INSTANCE;
//        singleton04.m();
//
        singleton01.setI();
        System.out.println( singleton01.getI() );
        Singleton01 s1 = Singleton01.getInstance();
        System.out.println( s1.getI() );
        System.out.println( s1.hashCode() );
        System.out.println( "---------------" );
        System.out.println( Singleton01.getInstance().hashCode() );


//        Class<?> singleton011 = Class.forName( "Singleton01" );
        Constructor<Singleton01> co = Singleton01.class.getDeclaredConstructor( null );
        co.setAccessible( true );
        Singleton01 s2 = co.newInstance( null );
        System.out.println( s2.getI() );
        System.out.println( "*************" );
        System.out.println( s2.hashCode() );
        System.out.println( (co.newInstance( null )).hashCode() );
    }
}
