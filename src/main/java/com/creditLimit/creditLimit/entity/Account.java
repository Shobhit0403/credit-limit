package com.creditLimit.creditLimit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @NonNull
    public String accountId;

    private String customerId;
    private Long accountLimit = 0L;
    private Long perTransactionLimit;
    private Long lastAccountLimit;
    private Long lastPerTransactionLimit;

    //will save in epoch
    private Long accountLimitUpdateDate;

    private Long perTransactionLimitUpdateDate;

}
