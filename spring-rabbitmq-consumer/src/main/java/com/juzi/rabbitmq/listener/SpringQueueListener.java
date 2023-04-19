package com.juzi.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class SpringQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("message = " + new String(message.getBody()));
    }
}
