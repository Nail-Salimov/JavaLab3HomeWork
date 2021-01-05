package ru.itis.documentcirculation.consumers.topic.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.documentcirculation.consumers.topic.callback.ActivateAccountCallback;
import ru.itis.documentcirculation.consumers.topic.callback.CashbackCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ActivateAccountConsumer {

    private final static String ACTIVATE_ROUTING_KEY = "account.blocked.*";
    private final static String TRANSACTION_EXCHANGE = "transactional_topic_exchange";
    private final static String EXCHANGE_TYPE = "topic";

    private ActivateAccountCallback callback;

    public ActivateAccountConsumer(ActivateAccountCallback callback) {
        this.callback = callback;
    }

    public void start(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(TRANSACTION_EXCHANGE, EXCHANGE_TYPE);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName,TRANSACTION_EXCHANGE , ACTIVATE_ROUTING_KEY);

            channel.basicConsume(queueName, true, callback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
