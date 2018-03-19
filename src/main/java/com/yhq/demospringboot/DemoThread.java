package com.yhq.demospringboot;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.apache.catalina.Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * yanghq
 * 2018/3/13
 */
public class DemoThread {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(2);
        Executors.newScheduledThreadPool(2);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        new LinkedBlockingQueue<>(2);
        new LinkedBlockingQueue<>();
        new ArrayQueue<>(2);
        new ArrayBlockingQueue<>(2);
    }
}
