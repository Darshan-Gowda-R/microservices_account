package com.kaveri_bank.account.service;

import com.kaveri_bank.account.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto dto);

    CustomerDto getAccount(String dto);

    void updateDto(CustomerDto dto);

    void deleteAccount(String mobileNumber);
    
}
