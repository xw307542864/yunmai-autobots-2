package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

public class TestDieLock {
    public static void main(String[] args) {
//        Lock1 t = new Lock1();
//        new Thread(() -> t.m1(),"t1").start();
//        new Thread(() -> t.m2(),"t2").start();

    }




    class Lock1{
        synchronized void m1(){
            System.out.println(Thread.currentThread().getName() + "m1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Lock2{
        synchronized void m2(){
            System.out.println(Thread.currentThread().getName() + "m2");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
