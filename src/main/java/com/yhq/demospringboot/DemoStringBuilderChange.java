package com.yhq.demospringboot;

public class DemoStringBuilderChange {

    public static void main(String[] args) {
        StringBuilder x = new StringBuilder("ab");
        change(x);
        System.out.println(x);
    }

    public static void change(StringBuilder x) {
        x.append("cd");
    }
}
