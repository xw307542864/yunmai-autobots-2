package com.logibeat.cloud.mq.producer;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BizNodeProducer {

    @NacosValue(value = "${rocketmq.name-server}", autoRefreshed = true)
    private String namesvrAddr;

    @NacosValue(value = "${biz.node.result.topic}", autoRefreshed = true)
    private String topic;

    @NacosValue(value = "${biz.node.result.topic.tag}", autoRefreshed = true)
    private String tag;

    @NacosValue(value = "${biz.node.consumergroup}", autoRefreshed = true)
    private String consumergroup;

    @NacosValue(value = "${merch.mq.ip}", autoRefreshed = true)
    private String consumerIp;

    private DefaultMQProducer producer;



    @PostConstruct
    public void initProducer() {
        producer = new DefaultMQProducer(consumergroup);
        producer.setNamesrvAddr(namesvrAddr);
        producer.setRetryTimesWhenSendFailed(3);
        try {
            producer.start();
            System.out.println("[BizNodeProducer 已启动]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String send(String msg) {
        SendResult result = null;
        try {
            Message message = new Message(topic, tag, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            result = producer.send(message);
            System.out.println("[BizNodeProducer] msgID(" + result.getMsgId() + ") " + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"MsgId\":\"" + result.getMsgId() + "\"}";
    }



}
