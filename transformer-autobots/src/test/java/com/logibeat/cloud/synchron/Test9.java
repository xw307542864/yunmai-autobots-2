package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

/**
 *
 */

public class Test9 {
    private Object obj = new Object();

    public void test(){
        synchronized (obj){
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }





    public static void main(String[] args) {
        Test9 t = new Test9();
        new Thread(() -> t.test(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(() -> t.test(), "t2");
//        t.obj = new Object();
        t2.start();
    }
}
