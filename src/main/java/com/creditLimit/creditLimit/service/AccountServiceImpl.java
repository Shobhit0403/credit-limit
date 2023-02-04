package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.repository.AccountRepository;
import com.creditLimit.creditLimit.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String createAccount(Account accountRequest) {


        if(Objects.nonNull(accountRepository.getById(accountRequest.getAccountId()))) {
            return "Account already exists";
        }
        accountRepository.save(accountRequest);
        return "Account created successfully"; //put in constants
    }

    @Override
    public Account getAccount(String accountId) {

        Account account = accountRepository.getById(accountId);

//        if(Objects.isNull(account))
//            return ResponseEntity.status(HttpStatus.valueOf(2))
//                    .body("Account does not exist");
        return account;
    }
}
