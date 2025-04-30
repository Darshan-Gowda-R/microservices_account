package com.kaveri_bank.account.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
    
    private HttpStatus errorCode;

    private String errorMsg;

    private String apiPath;

    private LocalDateTime errorTime;

}
