package com.logibeat.cloud.synchron;

/**
 * run（）加synchroized时只一个实例时是线程安全的
 *
 */
public class Test4 implements Runnable{
    private static int count = 10;
    private Object obj = new Object();

    @Override
    public synchronized void run() {
        count --;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "count = "+count);
    }



    public static void main(String[] args) {
        Test4 t = new Test4();
        new Thread(t).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t).start();
    }
}
