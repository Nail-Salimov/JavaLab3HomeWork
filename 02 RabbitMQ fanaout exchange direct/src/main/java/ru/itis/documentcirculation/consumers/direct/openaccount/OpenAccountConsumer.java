package ru.itis.documentcirculation.consumers.direct.openaccount;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class OpenAccountConsumer {

    private final static String OPEN_QUEUE = "open_queue";

    private OpenAccountCallBack deliverCallback;

    public OpenAccountConsumer(OpenAccountCallBack callBack){
        this.deliverCallback = callBack;
    }

    public void start() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicConsume(OPEN_QUEUE, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
