package com.logibeat.cloud.common.file;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wudi on 2017/5/31.
 */
public class CreateOrderNumber {
    private static final ReentrantLock lock =  new ReentrantLock();
    /**
     * 生成订单编号 规则   561+ 时间毫秒
     */
    public static String makeOrderNum() {
        lock.lock();
        StringBuilder orderNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        orderNumber.append("561");
        orderNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  orderNumber.toString();
    }

    /**
     * 生成运单号  运单号              562+ 时间毫秒
     * @return
     */
    public static  String makeWaybillNum(){
        lock.lock();
        StringBuilder waybillNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        waybillNumber.append("562");
        waybillNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  waybillNumber.toString();
    }

    /**
     * 生成配载单号              563+ 时间毫秒
     * @return
     */
    public static  String makeShippingNum(){
        lock.lock();
        StringBuilder shippingNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        shippingNumber.append("563");
        shippingNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  shippingNumber.toString();
    }
    
    /**
     * 生成派车单号              564+ 时间毫秒
                * @return
     */
        public static  String makeSendCarNum(){
            lock.lock();
            StringBuilder sendCarNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        sendCarNumber.append("564");
        sendCarNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  sendCarNumber.toString();
    }

    /**
     * 生成结算单号              565+ 时间毫秒
     * @return
     */
    public static  String makeSettlementNum(){
        lock.lock();
        StringBuilder settlementNum = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        settlementNum.append("565");
        settlementNum.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  settlementNum.toString();
    }

    /**
     * 生成派送单号              566+ 时间毫秒
     * @return
     */
    public static  String makeDeliveryNum(){
        lock.lock();
        StringBuilder deliveryNum = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        deliveryNum.append("566");
        deliveryNum.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  deliveryNum.toString();
    }

    /**
     * 生成揽收单号              567+ 时间毫秒
     * @return
     */
    public static  String makeCollectNum(){
        lock.lock();
        StringBuilder makeCollectNum = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        makeCollectNum.append("567");
        makeCollectNum.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  makeCollectNum.toString();
    }
    
    /**
     * 生成智慧商砼派车单号              568+ 时间毫秒
     * @return
     */
    public static  String makeConcreteNum(){
        lock.lock();
        StringBuilder makeCollectNum = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        makeCollectNum.append("568");
        makeCollectNum.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  makeCollectNum.toString();
    }
    
    //费用单
    public static String makeCostNum() {
        lock.lock();
        StringBuilder orderNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        orderNumber.append("570");
        orderNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  orderNumber.toString();
    }
    
    //询价单
    public static String makeInquiryNum() {
        lock.lock();
        StringBuilder orderNumber = new StringBuilder();
        // 取系统当前时间作为订单号变量前半部分，精确到毫秒
        String time =  String.valueOf(System.currentTimeMillis());
        orderNumber.append("571");
        orderNumber.append(time.substring(time.length()-12,time.length()));
        lock.unlock();
        return  orderNumber.toString();
    }
}
