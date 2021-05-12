package com.logibeat.cloud.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    Lock lock = new ReentrantLock();

    public void m1(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void m2(){
//        lock.lock();
        boolean b = lock.tryLock();
        try {
            TimeUnit.SECONDS.sleep(1);
            if(b){
                System.out.println(1);
            }else{
                System.out.println(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(b) lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        new Thread(() -> t.m1(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> t.m2(),"t2").start();

    }
}
