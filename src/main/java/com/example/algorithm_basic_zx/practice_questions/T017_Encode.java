package com.example.algorithm_basic_zx.practice_questions;

public class T017_Encode {
    public static void main(String[] args) {
//        String value = "aaaaaaaaaaaaaaddeewwwwbb";
//        String encode = encode( value );
//        System.out.println( encode );
//        String decode = decode( encode );
//        System.out.println( decode );
//        System.out.println( value.equals( decode ) );
        validate();
    }

    public static void validate() {
        int testTime = 50000;
        int maxLength = 100;
        String result = "nice";
        for (int i = 0; i < testTime; i++) {
            String input = generateRandomString( maxLength );
            System.out.println( input );
            String e1 = encode( input );
            System.out.println( "Encode:" + e1 );
            String d1 = decode( e1 );
            System.out.println( "Dncode:" + d1 );
            if (!input.equals( d1 )) {
                result = "oops";
                break;
            }
        }
        System.out.println( result );
    }

    private static String generateRandomString(int maxLength) {
        int len = (int) (Math.random() * maxLength);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int sameCharCount = (int) (Math.random() * maxLength / 2);
            if (len - sb.length() > sameCharCount) {
                char c = generateRandomAlph();
                for (int j = 0; j < sameCharCount; j++) {
                    sb.append( c );
                }
            } else {
                char c = generateRandomAlph();
                sb.append( c );
            }
        }
        return sb.toString();
    }

    private static char generateRandomAlph() {
        int position = (int) (Math.random() * 26);
        return (char) ('a' + position);
    }

    public static String encode(String value) {
        if (value == null) {
            return null;
        }
        char[] chars = value.toCharArray();
        if (chars.length == 0) {
            return "";
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        char prev = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (prev == chars[i]) {
                count++;
            } else {
                sb.append( count ).append( prev );
                prev = chars[i];
                count = 1;
            }
        }
        sb.append( count ).append( prev );
        return sb.toString();
    }

    public static String decode(String value) {
        if (value == null) {
            return null;
        }
        char[] chars = value.toCharArray();
        if (chars.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int prevNum = 0;
        for (char aChar : chars) {
            Integer currentValue = convertToInteger( aChar );
            if (currentValue == null) {
                sb.append( String.valueOf( aChar ).repeat( prevNum ) );
                prevNum = 0;
            } else {
                if (prevNum > 0) {
                    prevNum = prevNum * 10 + currentValue;
                } else {
                    prevNum = currentValue;
                }
            }
        }
        return sb.toString();
    }

    public static Integer convertToInteger(char c) {
        try {
            return Integer.parseInt( String.valueOf( c ) );
        } catch (Exception e) {
            return null;
        }
    }
}
