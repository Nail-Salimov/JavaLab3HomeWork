package ru.itis.documentcirculation.consumers.direct.openaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.documentcreator.OpenAccountDocumentCreator;
import ru.itis.documentcirculation.entities.account.AccountDto;
import ru.itis.documentcirculation.jsons.BankruptJson;
import ru.itis.documentcirculation.jsons.OpenAccountJson;
import ru.itis.documentcirculation.services.AccountsService;

import java.io.IOException;

@Component
public class OpenAccountCallBack implements DeliverCallback {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private OpenAccountDocumentCreator documentCreator;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(String consumerTag, Delivery delivery) throws IOException {
        String message = new String(delivery.getBody(), "UTF-8");
        OpenAccountJson openAccountJson = objectMapper.readValue(message, OpenAccountJson.class);


        AccountDto accountDto = openAccount(openAccountJson);
        createDocuments(openAccountJson, accountDto);
    }

    private AccountDto openAccount(OpenAccountJson json){
        return accountsService.createAccount(AccountDto.builder().build());
    }

    private void createDocuments(OpenAccountJson json, AccountDto accountDto){
        documentCreator.createDocument(json, accountDto.getId());
    }


}
