package com.juzi.consumer;

import com.juzi.util.MqUtil;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class ConsumerRouting1 {

    public static final String QUEUE_NAME = "test_direct_queue1";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = MqUtil.init();
        MqUtil.consumeMsg(connectionFactory, QUEUE_NAME);
    }
}
