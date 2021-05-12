package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

/**
 *
 */

public class Test8 {
    private volatile boolean running = true;

    public void count(){
        System.out.println("start ++");
        while (running){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end ++");
    }





    public static void main(String[] args) {
        Test8 t = new Test8();
        new Thread(() -> t.count(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
