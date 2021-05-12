package com.logibeat.cloud.synchron;

/**
 * 锁定的是当前实例
 * 如果test2是单例的话可以保证线程是安全的，如果不是并不能保证线程是安全的
 *
 */
public class Test2 {
    private int count = 10;
    private Object obj = new Object();

    public void test(){
        synchronized (this){//锁的是当前实例
            count --;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }

    public synchronized void test1(){ //同test()方法一样，都是锁定的当前实例
        synchronized (this){
            count --;
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        new Thread(() -> t.test()).start();
        new Thread(() -> t.test1()).start();
    }
}
