package com.creditLimit.creditLimit.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @NonNull
    public String accountId;

    private String customerId;
    private Long accountLimit = 0L;
    private Long perTransactionLimit = 0L;
    private Long lastAccountLimit = 0L;
    private Long lastPerTransactionLimit = 0L;

    private String accountLimitUpdateDate;

    private String perTransactionLimitUpdateDate;

}
