package com.logibeat.cloud;

public class TestException {
    public static void main(String[] args) {
        try{
            System.out.println("111111111111");
            return;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            System.out.println("fianly");
        }

    }
}
