package com.juzi.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class ConsumerHelloWorld {
    public static final String QUEUE_NAME = "hello_world";

    public static void main(String[] args) {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2、设置参数
        connectionFactory.setHost("rabbitmq.codejuzi.icu");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/codejuzi");
        connectionFactory.setUsername("codejuzi");
        connectionFactory.setPassword("codejuzi");
        try {
            // 3、创建连接
            Connection connection = connectionFactory.newConnection();
            // 4、创建Channel
            Channel channel = connection.createChannel();
            // 5、创建队列
            // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 6、接收消息
            // basicConsume(String queue, boolean autoAck, Consumer callback)
            channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    System.out.println("consumerTag = " + consumerTag);
                    String exchange = envelope.getExchange();
                    System.out.println("exchange = " + exchange);
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("routingKey = " + routingKey);
                    System.out.println("properties = " + properties);
                    String message = new String(body);
                    System.out.println("message = " + message);
                }
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}
