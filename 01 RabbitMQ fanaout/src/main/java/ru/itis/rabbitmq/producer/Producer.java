package ru.itis.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.rabbitmq.json.DocumentRequest;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final static String EXCHANGE_NAME = "document";
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
            DocumentRequest documentRequest = new DocumentRequest();

            System.out.println("Фамилиия");
            String surname = scanner.nextLine();

            System.out.println("Имя");
            String name = scanner.nextLine();

            System.out.println("Номер паспорта");
            String number = scanner.nextLine();

            System.out.println("Возраст");
            String age = scanner.nextLine();

            System.out.println("Дата выдачи");
            String date = scanner.nextLine();

            documentRequest.setSurname(surname);
            documentRequest.setName(name);
            documentRequest.setNumber(number);
            documentRequest.setAge(age);
            documentRequest.setDate(date);

            String requestJson = objectMapper.writeValueAsString(documentRequest);
            channel.basicPublish(EXCHANGE_NAME, "", null, requestJson.getBytes());

            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
