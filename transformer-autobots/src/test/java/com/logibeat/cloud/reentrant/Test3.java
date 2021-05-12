package com.logibeat.cloud.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {
    Lock lock = new ReentrantLock();

    public void m1(){
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }finally {
                lock.unlock();
            }
        }
    }



    public static void main(String[] args) {
        Test3 t = new Test3();
        new Thread(() -> t.m1(),"t1").start();
        new Thread(() -> t.m1(),"t2").start();

    }
}
