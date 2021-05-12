package com.logibeat.cloud.synchron;

/**
 * 修饰静态方法时锁的是.class文件
 * 静态方法中synchroized锁定代码块，锁定的对象不能是类的实例，只能是类.class文件
 * 类的.class文件是唯一的，所以修饰静态方法时，线程是安全的
 *
 */
public class Test3 {
    private static int count = 10;
    private Object obj = new Object();

    public static void test(){
        synchronized (Test3.class){
            count --;
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }

    public static synchronized void test1(){//同上面test()方法
        synchronized (Test3.class){
            count --;
            System.out.println(Thread.currentThread().getName() + "count = "+count);
        }
    }



    public static void main(String[] args) {
        Test3 t = new Test3();
        new Thread(() -> t.test()).start();
        new Thread(() -> t.test()).start();
    }
}
