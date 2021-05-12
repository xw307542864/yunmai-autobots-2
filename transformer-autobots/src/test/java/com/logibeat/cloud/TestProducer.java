package com.logibeat.cloud;

public class TestProducer {
    public static void main(String[] args) {
        Clazz clazz = new Clazz();
        Producer pro = new Producer(clazz);
        Consumer con = new Consumer(clazz);
        new Thread(pro).start();
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(con).start();
    }
}

class Student{
    int id;
    Student(int id){
        this.id = id;
    }
}

class Clazz{
    int index = 0;
    Student[] students = new Student[5];

    public synchronized void addStu(Student stu){
        while (index == students.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.notify();
        students[index] = stu;
        index ++;
    }

    public synchronized Student delStu(){
        while (index == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index --;
        return students[index];
    }
}

class Producer implements Runnable{
    Clazz clazz = null;
    public Producer(Clazz clazz){
        this.clazz = clazz;
    }
    public void run(){
        for (int i = 0; i < 10; i++) {
            Student stu = new Student(i);
            clazz.addStu(stu);
            System.out.println("producer : " + stu.id);
        }
    }
}

class Consumer implements Runnable{
    Clazz clazz = null;
    public Consumer(Clazz clazz){
        this.clazz = clazz;
    }
    public void run(){
        for (int i = 0; i < 10; i++) {
            Student stu = clazz.delStu();
            System.out.println("consumer : "+stu.id);
        }
    }
}