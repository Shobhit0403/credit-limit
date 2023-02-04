package com.creditLimit.creditLimit.service;

import com.creditLimit.creditLimit.entity.Account;
import com.creditLimit.creditLimit.entity.ActiveOffersRequest;
import com.creditLimit.creditLimit.entity.Offer;
import com.creditLimit.creditLimit.entity.UpdateRequest;

import java.util.List;

public interface OfferService {

    public String createOffer(Offer offerRequest) throws Exception;

    public List<Offer> getOffers(ActiveOffersRequest activeOffersRequest);

    public Account updateOffer(UpdateRequest updateRequest);
}
