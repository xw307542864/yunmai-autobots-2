package com.logibeat.cloud.synchron;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *
 */

public class TestVoliate {
    boolean running = true;

    public void test(){
        System.out.println("start ---");
        while (running){
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        System.out.println("end ---");
    }





    public static void main(String[] args) {
        TestVoliate t = new TestVoliate();
        new Thread(() -> t.test(),"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread t2 = new Thread(() -> t.test(), "t2");
//        t2.start();
        t.running = false;

        HashMap<String,Object> map = new HashMap<>();
        Collections.synchronizedMap(map);

    }
}
