package com.example.algorithm_basic_zx.practice_questions;

import java.util.HashMap;

public class A48_SetAll {
    public static class MyValue<V> {
        public V value;
        public long time;

        public MyValue(V value, long time) {
            this.value = value;
            this.time = time;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            this.map = new HashMap<>();
            this.time = 0;
            setAll = new MyValue<>( null, -1 );
        }

        public V put(K key, V value) {
            return map.put( key, new MyValue<>( value, time++ ) ).value;
        }

        public V get(K key) {
            MyValue<V> vMyValue = map.get( key );
            if (vMyValue == null) {
                return null;
            }
            return vMyValue.time < setAll.time ? setAll.value : vMyValue.value;
        }

        public void setAll(V value) {
            setAll = new MyValue<V>( value, time++ );
        }
    }
}
