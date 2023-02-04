package com.creditLimit.creditLimit.controller;


import com.creditLimit.creditLimit.entity.Account;
import com.creditLimit.creditLimit.entity.ActiveOffersRequest;
import com.creditLimit.creditLimit.entity.Offer;
import com.creditLimit.creditLimit.entity.UpdateRequest;
import com.creditLimit.creditLimit.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/create"
    )
    public ResponseEntity<?> creatLimitOffer(@RequestBody Offer offerRequest, BindingResult result) {

        if (result.hasErrors()) {
            log.error("BAD Request ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String offer;
        try {
            offer = offerService.createOffer(offerRequest);
        } catch (Exception e) {
            return new ResponseEntity<>("Not able to create limit offer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
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
