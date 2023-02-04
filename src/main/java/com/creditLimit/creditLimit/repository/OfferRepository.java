package com.creditLimit.creditLimit.repository;

import com.creditLimit.creditLimit.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByAccountIdAndOfferActivationDate(String accountId, String offerActivationDate);

    List<Offer> findAllByAccountId(String accountId);


}

