package com.kaveri_bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDto accountDto;

    public void setAccountDto(AccountsDto dto){
        this.accountDto = dto;
    }
    
}
