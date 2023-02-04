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
    private LimitType limitType;
    private String newLimit;
    private Long offerActivationDate;
    private String offerExpiryDate;

    private boolean active;

}
