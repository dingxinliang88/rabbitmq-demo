package com.juzi.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author codejuzi
 * @CreateTime 2023/4/19
 */
@Configuration
public class RabbitmqConfig {

    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_queue";


    /**
     * 声明交换机
     *
     * @return 交换机
     */
    @Bean("bootExchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    /**
     * 声明队列
     *
     * @return 队列
     */
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    /**
     * 往交换机上绑定队列
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return binding
     */
    @Bean
    public Binding bindingQueue2Exchange(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
