package com.juzi.rabbitmq;

import com.juzi.rabbitmq.config.RabbitmqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
@SpringBootTest
public class ProducerApplicationTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "boot.codejuzi", "hello, rabbitmq(springboot)!~~~~");
    }

}
