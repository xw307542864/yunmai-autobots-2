package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

/**
 * 写加锁,读不加锁容易出现脏读
 */
public class Test5 {
    private int count = 10;
    private Object obj = new Object();

    public synchronized void set(int count){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count = count;
    }

    public int get(){
        return this.count;
    }



    public static void main(String[] args) {
        Test5 t = new Test5();
        new Thread(() -> t.set(2)).start();
        try {
//            Thread.sleep(1000);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> System.out.println(t.get())).start();
    }
}
