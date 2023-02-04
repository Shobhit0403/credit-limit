package com.creditLimit.creditLimit.repository;

import com.creditLimit.creditLimit.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer, String> {

    List<Offer> findAllByAccountIdAndActive(String accountId, boolean active);

}

