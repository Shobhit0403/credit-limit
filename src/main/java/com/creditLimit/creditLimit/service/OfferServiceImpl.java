package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.*;
import com.creditLimit.creditLimit.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
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
        // create  offer built offer object
        // and save this in another table, offer
        // offer only ne created if only if  for a greater limit than current limit.
        //fetch account id

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
        return Strings.EMPTY;
    }

    private String saveOffer(Offer offerRequest) {
        offerRepository.save(offerRequest);
        return "offer created Successfully";
    }

    @Override
    public List<Offer> getOffers(ActiveOffersRequest activeOffersRequest) {

        String accountId = activeOffersRequest.getAccountId();
        Long activationDate = activeOffersRequest.getActivationDate();
        List<Offer> listOfActiveOffers;
        if(Objects.nonNull(activationDate)){
            listOfActiveOffers = offerRepository.findAllByAccountIdAndOfferActivationDate(accountId, activationDate);
        }
        else {
            listOfActiveOffers = offerRepository.findAllByAccountId(accountId);
        }
        return listOfActiveOffers.stream().filter(offer -> compareDate(offer.getOfferExpiryDate())).collect(Collectors.toList());
    }

    private boolean compareDate(String offerExpirationDate) {
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

    @Override
    public Account updateOffer(UpdateRequest updateRequest) {


        //using offer id find the offer from db
        //if the status is Accepted, then we need to update Account for the particular account
        //else delete that offer from db
//        if(updateRequest.)
//        Also check if the offer is active or not

        Optional<Offer> offer = offerRepository.findById(updateRequest.getOfferId());
        if(updateRequest.equals(StatusType.ACCEPTED) && offer.isPresent()){
            Account existingAccount = accountService.getAccount(offer.get().getAccountId());
//            existingAccount.setAccountLimit();
        }
        else if (updateRequest.equals(StatusType.REJECTED)) {
            offerRepository.deleteById(updateRequest.getOfferId());
        }
        return null;
    }
}
