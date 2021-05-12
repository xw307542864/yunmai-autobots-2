package com.logibeat.cloud.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    Lock lock = new ReentrantLock();

    public void m1(){
        int i = 0;
        lock.lock();
        try {
            while(true){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+" : " +i);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m2(){
        boolean b = true;
        try {
            System.out.println("start ++++++++");
            lock.lockInterruptibly();
            System.out.println("end ++++++++");
        } catch (InterruptedException e) {
            b = false;
            e.printStackTrace();
        }finally {
           if(b) lock.unlock();
        }

    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        new Thread(() -> t.m1(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(() -> t.m2(), "t2");
        t2.start();
        t2.interrupt();
    }
}
