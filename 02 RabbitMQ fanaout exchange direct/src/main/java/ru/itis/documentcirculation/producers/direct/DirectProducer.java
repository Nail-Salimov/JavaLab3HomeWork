package ru.itis.documentcirculation.producers.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.documentcirculation.jsons.BlockAccountJson;
import ru.itis.documentcirculation.jsons.OpenAccountJson;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class DirectProducer {

    private final static String OPEN_QUEUE = "open_queue";
    private final static String OPEN_ROUTING_KEY = "open";

    private final static String BLOCK_QUEUE = "block_queue";
    private final static String BLOCK_ROUTING_KEY = "block";

    private final static String EXCHANGE_NAME = "account";
    private final static String EXCHANGE_TYPE = "direct";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueBind(OPEN_QUEUE, EXCHANGE_NAME, OPEN_ROUTING_KEY);
            channel.queueBind(BLOCK_QUEUE, EXCHANGE_NAME, BLOCK_ROUTING_KEY);

            ObjectMapper objectMapper = new ObjectMapper();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Фамилиия");
            String surname = scanner.nextLine();

            System.out.println("Имя");
            String name = scanner.nextLine();

            System.out.println("Тип валюты");
            String currency = scanner.nextLine();

            OpenAccountJson openAccountJson = OpenAccountJson.builder()
                    .surname(surname)
                    .name(name)
                    .currency(currency)
                    .build();

            String json = objectMapper.writeValueAsString(openAccountJson);
            System.out.println(json);
            channel.basicPublish(EXCHANGE_NAME, OPEN_ROUTING_KEY, null, json.getBytes());

            BlockAccountJson blockAccountJson = BlockAccountJson.builder()
                    .id(1L)
                    .build();

            json = objectMapper.writeValueAsString(blockAccountJson);
            channel.basicPublish(EXCHANGE_NAME, BLOCK_ROUTING_KEY, null, json.getBytes());
            connection.close();
        }catch (IOException | TimeoutException e){
            throw new IllegalArgumentException(e);
        }
    }
}
