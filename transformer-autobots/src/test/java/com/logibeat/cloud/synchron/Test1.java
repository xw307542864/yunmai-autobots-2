package com.logibeat.cloud.synchron;

/**
 * 锁定的是对象，不是代码块
 */
public class Test1 {
    private int count = 10;
    private Object obj = new Object();

    public void test(){
        synchronized (obj){//锁的是对象，任何线程要执行这段代码，都要获得这个ojb对象的锁
            count --;
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        new Thread(() -> t.test()).start();
        new Thread(() -> t.test()).start();
    }
}
