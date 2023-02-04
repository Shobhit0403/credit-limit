package com.creditLimit.creditLimit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    @NonNull
    private String OfferId;
    @NonNull
    private StatusType status;
}
