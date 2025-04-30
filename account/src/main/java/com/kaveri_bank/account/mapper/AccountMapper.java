package com.kaveri_bank.account.mapper;

import com.kaveri_bank.account.dto.AccountsDto;
import com.kaveri_bank.account.entity.Accounts;

public class AccountMapper {

    public static AccountsDto toAccountDto(Accounts entity){

        return new AccountsDto(entity.getAccountNumber(),entity.getAccountType(),entity.getBranchAddress());

    }

    public static Accounts toAccountEntity(AccountsDto dto){

        return new Accounts(null,dto.getAccountNumber(),dto.getAccountType(),dto.getBranchAddress());

    }
    
}
