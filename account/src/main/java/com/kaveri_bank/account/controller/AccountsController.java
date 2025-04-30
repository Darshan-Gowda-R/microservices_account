package com.kaveri_bank.account.controller;

import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaveri_bank.account.constants.Constants;
import com.kaveri_bank.account.dto.CustomerDto;
import com.kaveri_bank.account.dto.ResponseDto;
import com.kaveri_bank.account.service.IAccountService;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
    
    IAccountService accountService;

    @GetMapping("hi")
    public String  sayHi(){

        return "Hi World of Microservices";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto dto){

        accountService.createAccount(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/account")
    public ResponseEntity<CustomerDto> getAccount(@RequestParam String mobileNumber){

        accountService.getAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto dto){

        accountService.updateDto(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto(Constants.MESSAGE_200, Constants.UPDATED));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){
        accountService.deleteAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseDto("Deleted the Account!!!", "DELETED"));
    }
}
