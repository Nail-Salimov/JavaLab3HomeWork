package ru.itis.documentcirculation.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itis.documentcirculation.consumers.direct.blockaccount.BlockAccountCallback;
import ru.itis.documentcirculation.consumers.direct.blockaccount.BlockAccountConsumer;
import ru.itis.documentcirculation.consumers.direct.openaccount.OpenAccountCallBack;
import ru.itis.documentcirculation.consumers.direct.openaccount.OpenAccountConsumer;
import ru.itis.documentcirculation.consumers.fanaout.callbacks.DbBankruptAccountsCallback;
import ru.itis.documentcirculation.consumers.fanaout.callbacks.DocumentBankruptAccountsCallBack;
import ru.itis.documentcirculation.consumers.fanaout.consumer.FanoutConsumer;
import ru.itis.documentcirculation.consumers.topic.callback.ActivateAccountCallback;
import ru.itis.documentcirculation.consumers.topic.callback.CashbackCallback;
import ru.itis.documentcirculation.consumers.topic.consumers.ActivateAccountConsumer;
import ru.itis.documentcirculation.consumers.topic.consumers.CashbackConsumer;

@Configuration
public class BeanConfiguration {

    @Bean
    public FanoutConsumer documentBankrupt(DocumentBankruptAccountsCallBack callBack){
        FanoutConsumer consumer = new FanoutConsumer(callBack);
        consumer.start();
        return consumer;
    }

    @Bean
    public FanoutConsumer dbBankrupt(DbBankruptAccountsCallback callBack){
        FanoutConsumer consumer = new FanoutConsumer(callBack);
        consumer.start();
        return consumer;
    }

    @Bean
    public OpenAccountConsumer openAccountConsumer(OpenAccountCallBack callBack){
        OpenAccountConsumer consumer = new OpenAccountConsumer(callBack);
        consumer.start();
        return consumer;
    }

    @Bean
    public BlockAccountConsumer blockAccountConsumer(BlockAccountCallback callBack){
        BlockAccountConsumer consumer = new BlockAccountConsumer(callBack);
        consumer.start();
        return consumer;
    }


    @Bean
    public CashbackConsumer cashbackConsumers(CashbackCallback callBack){
        CashbackConsumer consumer = new CashbackConsumer(callBack);
        consumer.start();
        return consumer;
    }

    @Bean
    public ActivateAccountConsumer activateBlockedAccountConsumers(ActivateAccountCallback callBack){
        ActivateAccountConsumer consumer = new ActivateAccountConsumer(callBack);
        consumer.start();
        return consumer;
    }
}
