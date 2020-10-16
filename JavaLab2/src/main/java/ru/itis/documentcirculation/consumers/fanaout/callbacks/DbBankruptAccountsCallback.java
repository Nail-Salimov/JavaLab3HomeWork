package ru.itis.documentcirculation.consumers.fanaout.callbacks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.entities.account.AccountDto;
import ru.itis.documentcirculation.jsons.BankruptJson;
import ru.itis.documentcirculation.services.AccountsService;

import java.io.IOException;

@Component
public class DbBankruptAccountsCallback implements DeliverCallback {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(String consumerTag, Delivery delivery) throws IOException {
        String message = new String(delivery.getBody(), "UTF-8");
        BankruptJson bankruptJson = objectMapper.readValue(message, BankruptJson.class);
        AccountDto accountDto = AccountDto.builder().id(bankruptJson.getId()).build();
        bankruptAccount(accountDto);
    }

    private boolean bankruptAccount(AccountDto accountDto) {
        return accountsService.bankrupt(accountDto);
    }


}
