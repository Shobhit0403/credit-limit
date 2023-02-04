package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.Account;

public interface IAccountService {

    public String createAccount(Account account);

    public Account getAccount(String accountId);

    public Account updateAccount(Account updatedAccount);


}
