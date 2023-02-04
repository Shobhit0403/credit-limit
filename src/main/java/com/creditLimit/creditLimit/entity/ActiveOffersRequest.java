package com.creditLimit.creditLimit.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveOffersRequest {

    @NonNull
    private String accountId;

    private String activationDate;

}
