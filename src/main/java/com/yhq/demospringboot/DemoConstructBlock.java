package com.yhq.demospringboot;

public class DemoConstructBlock {

    static {
        System.out.println("static");
    }

    {
        System.out.println("construct block");
    }

    public DemoConstructBlock() {
        System.out.println("construct");
    }

    public static void main(String[] args) {
        DemoConstructBlock demoConstructBlock1 = new DemoConstructBlock();
        DemoConstructBlock demoConstructBlock2 = new DemoConstructBlock();
    }
}
