package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    public String createAccount(Account account);

    public Account getAccount(String accountId);

    public Account updateAccount(Account updatedAccount);


}
