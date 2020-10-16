package ru.itis.documentcirculation.producers.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.documentcirculation.jsons.BankruptJson;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class FanoutProducer {

    private final static String EXCHANGE_NAME = "bankrupt";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            Scanner scanner = new Scanner(System.in);
            ObjectMapper objectMapper = new ObjectMapper();
            BankruptJson bankruptJson = new BankruptJson();

            System.out.println("Номер счета");
            String number = scanner.nextLine();

            System.out.println("Баланс");
            String money = scanner.nextLine();

            bankruptJson.setCurrentMoney(Long.valueOf(money));
            bankruptJson.setId(Long.valueOf(number));

            String requestJson = objectMapper.writeValueAsString(bankruptJson);
            channel.basicPublish(EXCHANGE_NAME, "", null, requestJson.getBytes());

            System.out.println(requestJson);

            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
