package com.yhq.demospringboot;

import net.rubyeye.xmemcached.HashAlgorithm;
import net.rubyeye.xmemcached.XMemcachedClient;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * yanghq
 * 2018/2/28
 */
public class WaitSleep {

    public static void main(String[] args) throws Exception{
//        testSleep();
//        testWait();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(new Thread());
//        executorService.submit(new Thread());

//        List<String> list = new ArrayList();
//        list.stream().filter(s -> s.equals(""));
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        System.out.println();

        XMemcachedClient client = new XMemcachedClient();
        List lq = new ArrayList();
        List mq = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            long hash = HashAlgorithm.KETAMA_HASH.hash(i+"");
            if(hash < 0) {
                System.out.println("hash:" + hash);
            }
            long mod = hash % 100;
            if(mod > 50) {
                mq.add(mod);
            } else {
                lq.add(mod);
            }
        }
        System.out.println(HashAlgorithm.computeMd5("d").length);
        System.out.println("lq is:" + lq.size());
        System.out.println("mq is:" + mq.size());
        System.out.println((1 << 32) - 1);
    }

    public static void testWait() throws Exception{
        WaitSleep waitSleep = new WaitSleep();
        synchronized (waitSleep) {
            System.out.println("============testWait" + new Date());
            waitSleep.wait();
        }
    }

    public static void testSleep() throws Exception{
        System.out.println("============testSleep" + new Date());
        Thread.sleep(3000);
    }

    public void testOverLay(int i) {

    }

    public Integer testOverLay(Integer i) {
        return null;
    }

    public Integer testOverLay(boolean i) {
        return null;
    }

    public void testOverLay(int i,int j) {

    }
}
