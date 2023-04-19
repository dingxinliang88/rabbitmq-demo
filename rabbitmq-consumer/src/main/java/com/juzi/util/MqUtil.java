package com.juzi.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class MqUtil {


    public static ConnectionFactory init() {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2、设置参数
        connectionFactory.setHost("rabbitmq.codejuzi.icu");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/codejuzi");
        connectionFactory.setUsername("codejuzi");
        connectionFactory.setPassword("codejuzi");
        return connectionFactory;
    }

    public static void consumeMsg(ConnectionFactory connectionFactory, String queueName) {
        try {
            // 3、创建连接
            Connection connection = connectionFactory.newConnection();
            // 4、创建Channel
            Channel channel = connection.createChannel();
            // 5、创建队列
            // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            channel.queueDeclare(queueName, true, false, false, null);
            // 6、接收消息
            // basicConsume(String queue, boolean autoAck, Consumer callback)
            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body);
                    System.out.println("message = " + message);
                }
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
