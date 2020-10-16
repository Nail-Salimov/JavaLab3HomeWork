package ru.itis.documentcirculation.services;


import ru.itis.documentcirculation.entities.account.AccountDto;

public interface AccountsService {

    boolean addMoney(AccountDto dto, long money);
    boolean withdraw(AccountDto dto, long money);

    boolean bankrupt(AccountDto dto);

    AccountDto createAccount(AccountDto accountDto);
    boolean blockAccount(AccountDto accountDto);
    boolean activateAccount(AccountDto accountDto);

    void cashbackAccount(Long money, Long id);
}
