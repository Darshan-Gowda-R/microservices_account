package com.kaveri_bank.account.mapper;

import com.kaveri_bank.account.dto.CustomerDto;
import com.kaveri_bank.account.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toCustomerDto(Customer customer){
        
        CustomerDto cus = new CustomerDto();
        cus.setName(customer.getName());
        cus.setEmail(customer.getEmail());
        cus.setMobileNumber(customer.getMobileNumber());
        return cus;
    }

    public static Customer toEntity(CustomerDto dto){

        return new Customer(null,dto.getName(),dto.getEmail(),dto.getMobileNumber());

    }
    
}
