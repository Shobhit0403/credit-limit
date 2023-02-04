package com.creditLimit.creditLimit.controller;

import com.creditLimit.creditLimit.entity.Account;
import com.creditLimit.creditLimit.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/account", consumes = "application/json", produces = "application/json")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<?> createAccount(@RequestBody Account account, BindingResult result) {

        if (result.hasErrors()) {
            log.error("BAD Request ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{couponCode}"

    )
    public ResponseEntity<?> getAccount(@PathVariable("couponCode") String accountId, BindingResult result) {
        if (result.hasErrors()) {
            log.error("BAD Request ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(Strings.isBlank(accountId)) {
            return ResponseEntity.status(HttpStatus.valueOf(1))
                    .body("Account id cannot be empty");
        }
        Account account;
        try {
             account = accountService.getAccount(accountId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(2)).body("Account does not exist");
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }


}
