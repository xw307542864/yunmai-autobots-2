package com.logibeat.cloud.synchron;

import java.util.concurrent.TimeUnit;

/**
 * 程序执行过程,如果出现异常,默认锁会被释放
 * 所以在并发处理的过程中,有异常要多加小心
 * 如果在一个app处理过程 多个servlet共同访问一个资源,如果异常处理不合适
 * 在第一个线程中抛出异常,其它线程就会进入代码中去,有可能访问到的是异常产生的数据
 */
public class Test7 {
    private int count = 0;

    public synchronized void count(){
        System.out.println("start ++");
        while (true){
            count++;
            System.out.println(count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 5){
                try{
                    count = count/0;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }





    public static void main(String[] args) {
        Test7 t = new Test7();
        new Thread(() -> t.count()).start();

    }
}
