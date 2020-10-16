package ru.itis.documentcirculation.consumers.direct.blockaccount;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BlockAccountConsumer {
    private final static String BLOCK_QUEUE = "block_queue";

    private BlockAccountCallback deliverCallback;

    public BlockAccountConsumer(BlockAccountCallback callBack) {
        this.deliverCallback = callBack;
    }

    public void start() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicConsume(BLOCK_QUEUE, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
