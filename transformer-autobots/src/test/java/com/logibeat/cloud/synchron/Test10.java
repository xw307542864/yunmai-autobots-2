package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

public class Test10 {
    Object o = new Object();
    int count = 0;
    public void m1(){
        synchronized (this){
            try {
                TimeUnit.SECONDS.sleep(2);
                count = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1 end");
        }
    }
    public synchronized void m2(){
        System.out.println(count);
    }

    public static void main(String[] args) {
        Test10 t = new Test10();
        new Thread(t::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m2).start();
    }
}
