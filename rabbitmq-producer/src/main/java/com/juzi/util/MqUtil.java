package com.juzi.util;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class MqUtil {

    public static ConnectionFactory init() {
        // 1、创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2、设置参数
        connectionFactory.setHost("${your host}");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("your username");
        connectionFactory.setPassword("your password");
        return connectionFactory;
    }
}
