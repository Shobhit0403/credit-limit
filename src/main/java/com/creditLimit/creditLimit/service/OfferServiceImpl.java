package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.*;
import com.creditLimit.creditLimit.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService{

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public String createOffer(Offer offerRequest) throws Exception{

        String accountId = offerRequest.getAccountId();
        Account account = accountService.getAccount(accountId);
        switch (offerRequest.getLimitType()){
            case ACCOUNT_LIMIT:
                if(offerRequest.getNewLimit() > account.getAccountLimit()){
                    return saveOffer(offerRequest);
                }
                break;
            case PER_TRANSACTION_LIMIT:
                if(offerRequest.getNewLimit() > account.getPerTransactionLimit()){
                    return saveOffer(offerRequest);
                }
                break;
            default:
                log.error("Invalid limit type found");
                throw new Exception("Invalid limit type found");
        }
        return "Limit is smaller then current limit";
    }

    private String saveOffer(Offer offerRequest) throws ParseException {
        //check activation date should be greater than expiry
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(sdf.parse(offerRequest.getOfferExpiryDate()).compareTo(sdf.parse(offerRequest.getOfferActivationDate()))>0) {
            try {
                offerRepository.save(offerRequest);
            } catch (JpaSystemException e) {
                throw new RuntimeException("Not able to fetch value from db");
            }
            return "offer created Successfully \n" + "Result - " + offerRequest.toString();
        }
        else {
            return "Invalid Request";
        }
    }

    @Override
    public List<Offer> getOffers(ActiveOffersRequest activeOffersRequest) {

        String accountId = activeOffersRequest.getAccountId();
        String activationDate = activeOffersRequest.getActivationDate();
        List<Offer> listOfActiveOffers;
        if(Objects.nonNull(activationDate)){
            try {
                listOfActiveOffers = offerRepository.findAllByAccountIdAndOfferActivationDate(accountId, activationDate);
            } catch (JpaSystemException e) {
                throw new RuntimeException("Not able to fetch value from db");
            }
        }
        else {
            try {
                listOfActiveOffers = offerRepository.findAllByAccountId(accountId);
            } catch (JpaSystemException e) {
                throw new RuntimeException("Not able to fetch value from db");
            }
        }
        return listOfActiveOffers.stream().filter(offer -> isOfferActive(offer.getOfferExpiryDate())).collect(Collectors.toList());
    }

    @Override
    public Account updateOffer(UpdateRequest updateRequest) {

        if(Objects.isNull(updateRequest.getOfferId())) {
            log.error("Status type is not selected");
            throw new NullPointerException("offer Id is null");
        }

        if(updateRequest.getStatus().equals(StatusType.INVALID)) {
            log.warn("Status type is not selected");
            return new Account();
        }
        Account updatedAccount = new Account();
        Optional<Offer> optionalOffer = offerRepository.findById(updateRequest.getOfferId());
        if(optionalOffer.isPresent()) {
            Offer currentOffer = optionalOffer.get();
            if(updateRequest.getStatus().equals(StatusType.ACCEPTED) && isOfferActive(currentOffer.getOfferExpiryDate())){
                Account existingAccount = accountService.getAccount(currentOffer.getAccountId());
                existingAccount.setAccountLimit(currentOffer.getNewLimit());
                updatedAccount = accountService.updateAccount(existingAccount);
                log.info("Account is updated");
            }
            offerRepository.deleteById(updateRequest.getOfferId());
            return updatedAccount;
        }
        //offer not present exception
        log.info("Account is not updated");
        return updatedAccount;
    }

    private boolean isOfferActive(String offerExpirationDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        Date expirationDate;
        try {
            expirationDate = sdf.parse(offerExpirationDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return currentDate.compareTo(expirationDate)<0;
    }
}
