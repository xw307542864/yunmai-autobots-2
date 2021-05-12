package com.logibeat.cloud.synchron;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock生产者消费者
 *
 */
public class TestProCon {
    public static void main(String[] args) {
        Class aClass = new Class();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                Person stu = null;
                try {
                    stu = aClass.getStu();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(stu.id);
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                try {
                    aClass.setStu(new Person(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"producer").start();
    }



}

class Person  {
    public int id;
    public Person(int id){
        this.id = id;
    }
}

class Class{
    private List<Person> personArray = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition proCondition = lock.newCondition();
    private Condition conCondition = lock.newCondition();
    public void setStu(Person stu) throws InterruptedException {
        StringBuffer sbf = new StringBuffer();
        lock.lock();
        try {
            while (personArray.size() == 5){
                //生产满了，先等待
                proCondition.await();
            }
            personArray.add(stu);
            System.out.println(Thread.currentThread().getName() + " : " + stu.id);
            //生产好可以消费了
            conCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    public Person getStu() throws InterruptedException {
        Person person = null;
        lock.lock();
        try {
            while (personArray.size() == 0){
                //消费完了等待
                conCondition.await();
            }
            person = personArray.get(0);
            System.out.println(personArray.remove(0));
            System.out.println(Thread.currentThread().getName() + " : " + person.id);
            //消费了，可以生产了
            proCondition.signal();
        }finally {
            lock.unlock();
        }

        return person;
    }
}
