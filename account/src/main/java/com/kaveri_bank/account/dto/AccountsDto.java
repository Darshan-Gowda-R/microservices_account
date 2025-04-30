package com.kaveri_bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class AccountsDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
    
}
