package com.juzi.consumer;

import com.juzi.util.MqUtil;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class ConsumerWorkQueue2 {

    public static final String WORK_QUEUE_NAME = "work_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = MqUtil.init();
        MqUtil.consumeMsg(connectionFactory, WORK_QUEUE_NAME);
    }
}
