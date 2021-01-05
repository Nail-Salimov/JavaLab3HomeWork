package ru.itis.documentcirculation.producers.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.documentcirculation.jsons.CashbackJson;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicProducer {

    private final static String CASHBACK_ACTIVE_ROUTING_KEY = "account.active.cashback";
    private final static String CASHBACK_BLOCKED_ROUTING_KEY = "account.blocked.cashback";
    private final static String TRANSACTION_EXCHANGE = "transactional_topic_exchange";
    private final static String EXCHANGE_TYPE = "topic";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(TRANSACTION_EXCHANGE, EXCHANGE_TYPE);

            ObjectMapper objectMapper = new ObjectMapper();
            CashbackJson cashbackJson = CashbackJson.builder().money(323L).id(2L).build();
            String json = objectMapper.writeValueAsString(cashbackJson);

            channel.basicPublish(TRANSACTION_EXCHANGE, CASHBACK_BLOCKED_ROUTING_KEY, null, json.getBytes());

            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
