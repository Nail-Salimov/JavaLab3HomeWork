package ru.itis.rabbitmq.consumers.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private final static String EXCHANGE_NAME = "document";
    private final static String EXCHANGE_TYPE = "fanout";
    private DeliverCallback deliverCallback;

    public Consumer(DeliverCallback deliverCallback) {
        this.deliverCallback = deliverCallback;
    }

    public void start() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
