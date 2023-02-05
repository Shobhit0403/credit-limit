package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.repository.AccountRepository;
import com.creditLimit.creditLimit.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

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
            return accountRepository.findById(accountId).orElse(null);
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
