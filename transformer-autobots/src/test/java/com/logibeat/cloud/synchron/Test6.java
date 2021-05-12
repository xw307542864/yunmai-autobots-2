package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调另下个同步方法,一个线程已经拥有某人对象的锁
 * ,再次申请时仍然可以得到该对象的锁
 * 可重入锁
 */
public class Test6 {
    private int count = 10;
    private Object obj = new Object();

    public synchronized void set(int count){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count = count;
        get();
    }

    public synchronized int get(){
        System.out.println("get Start");
        return this.count;
    }



    public static void main(String[] args) {
        Test6 t = new Test6();
        new Thread(() -> t.set(2)).start();

    }
}
