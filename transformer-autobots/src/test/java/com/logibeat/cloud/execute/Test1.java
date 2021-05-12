package com.logibeat.cloud.execute;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(10);
        taskExecutor.schedule(()->{
                System.out.println("来了老弟");
        },10, TimeUnit.SECONDS);
        System.out.println(1111);
        System.out.println(taskExecutor.getActiveCount());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(taskExecutor.getActiveCount());
    }
}
