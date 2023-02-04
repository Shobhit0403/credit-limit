package com.creditLimit.creditLimit.repository;


import com.creditLimit.creditLimit.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {

}
