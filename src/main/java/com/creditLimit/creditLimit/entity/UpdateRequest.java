package com.creditLimit.creditLimit.entity;

import lombok.NonNull;

public class UpdateRequest {

    @NonNull
    private String OfferId;
    @NonNull
    private StatusType status;
}
