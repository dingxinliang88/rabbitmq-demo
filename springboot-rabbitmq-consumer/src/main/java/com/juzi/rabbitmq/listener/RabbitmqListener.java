package com.juzi.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
@Component
public class RabbitmqListener {

    @RabbitListener(queues = "boot_queue")
    public void listenMessage(Message message) {
        System.out.println("message = " + new String(message.getBody()));
    }
}
