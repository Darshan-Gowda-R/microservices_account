package com.kaveri_bank.account.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kaveri_bank.account.dto.ErrorMessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlredyExist.class)
    public ResponseEntity<ErrorMessageDto> handleCustomerAlredyExistsException(CustomerAlredyExist exception , WebRequest request){

        ErrorMessageDto errorMessageDto = new ErrorMessageDto( HttpStatus.BAD_REQUEST,exception.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorMessageDto,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorMessageDto> handleCustomerAlredyExistsException(ResourceNotFound exception , WebRequest request){

        ErrorMessageDto errorMessageDto = new ErrorMessageDto( HttpStatus.NOT_FOUND,exception.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorMessageDto,HttpStatus.BAD_REQUEST);

    }

    
}
