package com.juzi.consumer;

import com.juzi.util.MqUtil;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class ConsumerTopic2 {

    public static final String QUEUE_NAME = "test_topic_queue2";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = MqUtil.init();
        MqUtil.consumeMsg(connectionFactory, QUEUE_NAME);
    }
}
