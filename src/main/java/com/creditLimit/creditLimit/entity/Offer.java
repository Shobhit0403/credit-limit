package com.creditLimit.creditLimit.entity;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @NonNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)    private Long OfferId;
    private String accountId;

    @NonNull
    private LimitType limitType = LimitType.INVALID;
    private Long newLimit;
    private String offerActivationDate;
    private String offerExpiryDate;

}
