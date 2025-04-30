package com.kaveri_bank.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlredyExist extends RuntimeException{
    
    public CustomerAlredyExist(String message){
        super(message);
    }

}
