package com.juzi.producer;

import com.juzi.util.MqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
public class ProducerWorkQueue {

    public static final String QUEUE_NAME = "work_queue";
    public static final String ROUTING_KEY = "work_queue";
    public static final int MESSAGE_NUM = 10;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = MqUtil.init();
        Connection connection = null;
        Channel channel = null;
        try {
            // 3、创建连接
            connection = connectionFactory.newConnection();
            // 4、创建Channel
            channel = connection.createChannel();
            // 5、创建队列
            // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 6、发送消息
            for (int i = 0; i < MESSAGE_NUM; i++) {
                String message = "hello world~~~ ===> " + i;
                // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
                channel.basicPublish("", ROUTING_KEY, null, message.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                // 7、释放资源
                Objects.requireNonNull(channel).close();
                connection.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
