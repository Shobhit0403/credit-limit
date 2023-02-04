package com.creditLimit.creditLimit.controller;


import com.creditLimit.creditLimit.entity.Account;
import com.creditLimit.creditLimit.entity.ActiveOffersRequest;
import com.creditLimit.creditLimit.entity.Offer;
import com.creditLimit.creditLimit.entity.UpdateRequest;
import com.creditLimit.creditLimit.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/offer", consumes = "application/json", produces = "application/json")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/create"
    )
    public Offer creatLimitOffer(@RequestBody Offer offerRequest) {
        return offerService.createOffer(offerRequest);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/update"
    )
    public Account updateOffer(@RequestBody UpdateRequest updateRequest) {
        return offerService.updateOffer(updateRequest);
    }
    @RequestMapping(
            method = RequestMethod.GET
    )
    public List<Offer> getOffer(@RequestBody ActiveOffersRequest activeOffersRequest){
        return offerService.getOffers(activeOffersRequest);
    }
}
