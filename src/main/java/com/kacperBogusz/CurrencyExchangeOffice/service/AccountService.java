package com.kacperBogusz.CurrencyExchangeOffice.service;

import com.kacperBogusz.CurrencyExchangeOffice.domain.Account;
import com.kacperBogusz.CurrencyExchangeOffice.dto.CreateAccountDto;
import com.kacperBogusz.CurrencyExchangeOffice.exception.AccountAlreadyExistException;
import com.kacperBogusz.CurrencyExchangeOffice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account create(CreateAccountDto dto) {


        Account account = Account.builder()
                .pesel(dto.getPesel())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
        repository.save(account);
        return account;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findAccountByPesel(String pesel) {
        return repository.getOne(pesel);
    }


//    public boolean existsAccountInput(CreateAccountDto dto) throws AccountAlreadyExistException {
//    }
}
