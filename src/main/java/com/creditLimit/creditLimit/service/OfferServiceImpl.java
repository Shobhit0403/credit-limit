package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.*;
import com.creditLimit.creditLimit.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Offer createOffer(Offer offerRequest) {
        // create  offer built offer object
        // and save this in another table, offer
        // offer only ne created if only if  for a greater limit than current limit.
        //fetch account id

        String accountId = offerRequest.getAccountId();
        Account account = accountService.getAccount(accountId);
        if(offerRequest.getLimitType() == LimitType.ACCOUNT_LIMIT){
            if(offerRequest.getNewLimit() > account.getAccountLimit()){
                //create an offer
            }
        }
        else if(offerRequest.getLimitType() == LimitType.PER_TRANSACTION_LIMIT){
            if(offerRequest.getNewLimit() > account.getPerTransactionLimit()){
                //create an offer
            }
        }
        else {
            return new Offer();
        }
        return offerRequest;
    }

    @Override
    public List<Offer> getOffers(ActiveOffersRequest activeOffersRequest) {
        String accountId = activeOffersRequest.getAccountId();
        Long activationDate = activeOffersRequest.getActivationDate();

        if(Objects.nonNull()){
            return offerRepository.findAllByAccountIdAndActive(accountId, activationDate);
        }
        else {
            return offerRepository.findAllByAccountIdAndActive();
        }

        // get all the active offers date by account id query
        // activation date is given then stream and filter on the basis of data, else return all

        return null;
    }

    @Override
    public Account updateOffer(UpdateRequest updateRequest) {

//        if the status is Accepted, then we need to update Account for the particular account
        //else



        return null;
    }
}
