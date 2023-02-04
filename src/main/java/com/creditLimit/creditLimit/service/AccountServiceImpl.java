package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.repository.AccountRepository;
import com.creditLimit.creditLimit.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String createAccount(Account accountRequest) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String date = sdf.format(currentDate);

            if(accountRepository.findById(accountRequest.getAccountId()).isPresent())
                return "Account already exist";
            else {
                Account newAccount = accountRequest;
                newAccount.setAccountLimitUpdateDate(date);
                newAccount.setPerTransactionLimitUpdateDate(date);
                //handle exception
                try {
                    accountRepository.save(accountRequest);
                } catch (JpaSystemException e) {
                    throw new RuntimeException("Not able to save value from db");
                }
                return accountRequest.toString();
            }
    }

    @Override
    public Account getAccount(String accountId) {
        try {
            return accountRepository.findById(accountId).orElse(new Account());
        } catch (JpaSystemException e) {
            throw new RuntimeException("Not able to save value from db");
        }
    }

    @Override
    public Account updateAccount(Account updatedAccount) {
        try {
            accountRepository.save(updatedAccount);
        } catch (JpaSystemException e) {
            throw new RuntimeException("Not able to save value from db");
        }
        return updatedAccount;
    }
}
