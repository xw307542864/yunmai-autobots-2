package com.logibeat.cloud.execute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoinpool实现计算1-100000的和
 */
public class Test2 {
//    public static void main(String[] args) {
//        List<Long> list = new ArrayList<>();
//
//        for (int i = 0; i < 10000000; i++) {
//            list.add((long)i);
//        }
//        long start = System.currentTimeMillis();
//        Long total = list.stream().reduce((sum, p) -> sum + p).get();
//        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(total);
//        long astart = System.currentTimeMillis();
//        Long aLong = list.parallelStream().reduce((sum, p) -> sum + p).get();
//        System.out.println(System.currentTimeMillis() - astart);
//        System.out.println(aLong);
//
//    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ForkJoinPool joinPool = new ForkJoinPool();
        AddTask task = new AddTask(1,1000000);
        joinPool.execute(task);
        System.out.println(task.join());
        System.out.println(System.currentTimeMillis() - startTime);
        try {
            //主线程阻塞
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class AddTask extends RecursiveTask<Long> {
        int start;
        int end;
        public AddTask(int start,int end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            if(end - start <= 50000){
                Long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum +=i;
                }
                System.out.println("start : "+start + " end : "+end+" sum : " +sum);
                return sum;

            }
            int module = (start + end)/2;
            AddTask task1 = new AddTask(start,module);
            AddTask task2 = new AddTask(module,end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
