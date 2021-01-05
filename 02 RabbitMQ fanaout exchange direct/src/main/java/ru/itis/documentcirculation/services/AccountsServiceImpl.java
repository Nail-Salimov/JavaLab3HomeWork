package ru.itis.documentcirculation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.documentcirculation.entities.account.Account;
import ru.itis.documentcirculation.entities.account.AccountDto;
import ru.itis.documentcirculation.entities.account.State;
import ru.itis.documentcirculation.repositories.AccountsRepository;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public boolean addMoney(AccountDto dto, long money) {
        return changeBalance(dto, money);
    }

    @Override
    public boolean withdraw(AccountDto dto, long money) {
        return changeBalance(dto, -money);
    }

    @Override
    public boolean bankrupt(AccountDto dto) {
        Optional<Account> accountOptional = accountsRepository.findById(dto.getId());
        if (!accountOptional.isPresent()) {
            return false;
        }
        Account account = accountOptional.get();
        account.setState(State.BANKRUPT);
        account.setMoney(0L);
        accountsRepository.save(account);
        return true;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account =Account.builder()
                .money(0L)
                .state(State.ACTIVE)
                .build();
        accountsRepository.save(account);

        return AccountDto.builder()
                .id(account.getId())
                .money(account.getMoney())
                .state(account.getState())
                .build();
    }

    @Override
    public boolean blockAccount(AccountDto accountDto) {
        Optional<Account> accountOptional = accountsRepository.findById(accountDto.getId());
        if (!accountOptional.isPresent()) {
            return false;
        }
        Account account = accountOptional.get();
        account.setState(State.BLOCKED);
        accountsRepository.save(account);
        return true;
    }

    @Override
    public boolean activateAccount(AccountDto accountDto) {
        Optional<Account> accountOptional = accountsRepository.findById(accountDto.getId());
        if (!accountOptional.isPresent()) {
            return false;
        }
        Account account = accountOptional.get();
        account.setState(State.ACTIVE);
        accountsRepository.save(account);
        return true;
    }

    @Override
    public void cashbackAccount(Long money, Long id) {
        accountsRepository.cashbackAccount(money, id);
    }

    private boolean changeBalance(AccountDto dto, long money) {
        Optional<Account> accountOptional = accountsRepository.findById(dto.getId());
        if (!accountOptional.isPresent()) {
            return false;
        }
        Account account = accountOptional.get();
        account.setMoney(account.getMoney() + money);
        accountsRepository.save(account);
        return true;
    }

}
