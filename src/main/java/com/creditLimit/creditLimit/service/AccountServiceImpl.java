package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.repository.AccountRepository;
import com.creditLimit.creditLimit.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String createAccount(Account accountRequest) {
            if((accountRepository.findById(accountRequest.getAccountId()).isPresent()))
                return "Account already exist";
            else {
                accountRepository.save(accountRequest);
                return "Account created successfully";
            }
    }

    @Override
    public Account getAccount(String accountId) {

        return accountRepository.findById(accountId).orElse(null);

    }
}
