package com.yhq.demospringboot;

public class DemoString {

    public static void main(String[] args) {
        String a  = "abc";
        String b = "abc";
        String c = "a" + "b" + "c";
        String a1 = "a";
        String b1 = a1 + "bc";
        String d = new String("abc");
        String e = new StringBuilder("abc").toString();
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b1.intern() == c);
        System.out.println(b1 == c);
        System.out.println(d.intern() == c);
        System.out.println(d == c);
        System.out.println(e == c);
    }
}
