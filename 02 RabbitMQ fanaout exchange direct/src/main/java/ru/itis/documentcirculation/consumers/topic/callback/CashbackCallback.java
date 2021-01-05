package ru.itis.documentcirculation.consumers.topic.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.jsons.CashbackJson;
import ru.itis.documentcirculation.services.AccountsService;

import java.io.IOException;

@Component
public class CashbackCallback implements DeliverCallback {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountsService accountsService;

    @Override
    public void handle(String consumerTag, Delivery message) throws IOException {
        String mes = new String(message.getBody(), "UTF-8");
        CashbackJson cashbackJson = objectMapper.readValue(mes, CashbackJson.class);
        accountsService.cashbackAccount(cashbackJson.getMoney(), cashbackJson.getId());
    }
}
