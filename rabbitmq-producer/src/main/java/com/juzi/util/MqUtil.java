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
        connectionFactory.setHost("rabbitmq.codejuzi.icu");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/codejuzi");
        connectionFactory.setUsername("codejuzi");
        connectionFactory.setPassword("codejuzi");
        return connectionFactory;
    }
}
