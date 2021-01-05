package ru.itis.documentcirculation.consumers.direct.blockaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.documentcirculation.entities.account.AccountDto;
import ru.itis.documentcirculation.jsons.BlockAccountJson;
import ru.itis.documentcirculation.jsons.OpenAccountJson;
import ru.itis.documentcirculation.services.AccountsService;

import java.io.IOException;

@Component
public class BlockAccountCallback implements DeliverCallback {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountsService accountsService;

    @Override
    public void handle(String consumerTag, Delivery message) throws IOException {
        String mes = new String(message.getBody(), "UTF-8");
        BlockAccountJson blockAccountJson = objectMapper.readValue(mes, BlockAccountJson.class);
        blockAccount(blockAccountJson);
    }

    private void blockAccount(BlockAccountJson json){
        AccountDto accountDto = AccountDto.builder().id(json.getId()).build();
        accountsService.blockAccount(accountDto);
    }
}
