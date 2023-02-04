package com.creditLimit.creditLimit.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @NonNull
    @Generated
    private String OfferId;
    private String accountId;

    @NonNull
    private LimitType limitType = LimitType.INVALID;
    private Long newLimit;
    private String offerActivationDate;
    private String offerExpiryDate;

}
