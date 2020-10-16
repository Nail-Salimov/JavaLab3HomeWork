package ru.itis.documentcirculation.consumers.fanaout.callbacks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.documentcreator.BankruptDocumentCreator;
import ru.itis.documentcirculation.jsons.BankruptJson;

import java.io.IOException;

@Component
public class DocumentBankruptAccountsCallBack implements DeliverCallback {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankruptDocumentCreator documentCreator;

    @Override
    public void handle(String consumerTag, Delivery delivery) throws IOException {
        String message = new String(delivery.getBody(), "UTF-8");
        BankruptJson bankruptJson = objectMapper.readValue(message, BankruptJson.class);
        createDocuments(bankruptJson);
    }

    private void createDocuments(BankruptJson json){
        documentCreator.createDocument(json);
    }
}
