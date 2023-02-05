package com.creditLimit.creditLimit.entity;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveOffersRequest {

    @NonNull
    private String accountId;

    private String activationDate;

}
