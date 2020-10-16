package ru.itis.documentcirculation.consumers.fanaout.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutConsumer {

    private final static String EXCHANGE_NAME = "bankrupt";
    private final static String EXCHANGE_TYPE = "fanout";
    private DeliverCallback deliverCallback;

    public FanoutConsumer(DeliverCallback deliverCallback) {
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

            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {

            });

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
