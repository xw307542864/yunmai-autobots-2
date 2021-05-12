package com.logibeat.cloud.synchron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 一个容器add size两个方法 ,线程1add(),线程2监控,当线程1加到5后提示
 */
public class TestMian {
    volatile List<Integer> list = new ArrayList<>();
    Object o = new Object();
    public void add(int i) throws InterruptedException {
        synchronized (o){
            System.out.println(i);
            list.add(i);
            if(list.size() == 5){
                o.notify();
                o.wait();
            }
        }
    }

    public void size() throws InterruptedException {
        synchronized (o){
            o.wait();
            System.out.println("已经到5个了");
            o.notify();
        }
    }

    public static void main(String[] args) {
        TestMian t = new TestMian();

        new Thread(() ->{
            try {
                t.size();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    t.add(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
