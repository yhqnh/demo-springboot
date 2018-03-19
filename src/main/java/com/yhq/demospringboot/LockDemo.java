package com.yhq.demospringboot;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * yanghq
 * 2018/3/6
 */
public class LockDemo {

    private final Lock lock = new ReentrantLock();

    private final Condition addCondition = lock.newCondition();

    private final Condition subCondition = lock.newCondition();

    private static int num = 0;
    private List<String> lists = new LinkedList<String>();

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();

        Thread t1=new Thread(new AddThread(lockDemo));
        Thread t3=new Thread(new AddThread(lockDemo));
        Thread t7=new Thread(new AddThread(lockDemo));
        Thread t8=new Thread(new AddThread(lockDemo));
        Thread t2 = new Thread(new SubThread(lockDemo));
        Thread t4 = new Thread(new SubThread(lockDemo));
        Thread t5 = new Thread(new SubThread(lockDemo));
        Thread t6 = new Thread(new SubThread(lockDemo));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
    }

    public void add() {
        lock.lock();

        try {
            while(lists.size() == 10) {//当集合已满,则"添加"线程等待
                addCondition.await();
            }

            num++;
            lists.add("add Banana" + num);
            System.out.println("The Lists Size is " + lists.size());
            System.out.println("The Current Thread is " + Thread.currentThread().getName());
            System.out.println("==============================");
            this.subCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {//释放锁
            lock.unlock();
        }
    }


    public void sub() {
        lock.lock();

        try {
            while(lists.size() == 0) {//当集合为空时,"减少"线程等待
                subCondition.await();
            }

            String str = lists.get(0);
            lists.remove(0);
            System.out.println("The Token Banana is [" + str + "]");
            System.out.println("The Current Thread is " + Thread.currentThread().getName());
            System.out.println("==============================");
            num--;
            addCondition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class AddThread implements Runnable {

    private LockDemo task;

    public AddThread(LockDemo task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.add();
    }
}

class SubThread implements Runnable {

    private LockDemo task;

    public SubThread(LockDemo task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.sub();
    }

}