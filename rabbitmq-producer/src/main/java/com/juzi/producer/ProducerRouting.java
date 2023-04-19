package com.juzi.producer;

import com.juzi.util.MqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
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
public class ProducerRouting {
    public static final String QUEUE_NAME_1 = "test_direct_queue1";
    public static final String QUEUE_NAME_2 = "test_direct_queue2";
    public static final String EXCHANGE_NAME = "test_direct";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = MqUtil.init();
        Connection connection = null;
        Channel channel = null;
        try {
            // 3、创建连接
            connection = connectionFactory.newConnection();
            // 4、创建Channel
            channel = connection.createChannel();
            // 5、创建交换机
            // exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, false, null);
            // 6、创建队列
            // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
            channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
            // 7、绑定交换机
            // queueBind(String queue, String exchange, String routingKey)
            channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "error");
            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "info");
            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "warning");
            channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "error");
            // 8、发送消息
            String message = "[log]: tom call findAll() method... type: error";
            // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                // 9、释放资源
                Objects.requireNonNull(channel).close();
                connection.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
