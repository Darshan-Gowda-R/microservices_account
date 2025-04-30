package com.kaveri_bank.account.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kaveri_bank.account.AccountApplication;
import com.kaveri_bank.account.constants.Constants;
import com.kaveri_bank.account.dto.AccountsDto;
import com.kaveri_bank.account.dto.CustomerDto;
import com.kaveri_bank.account.entity.Accounts;
import com.kaveri_bank.account.entity.Customer;
import com.kaveri_bank.account.exception.CustomerAlredyExist;
import com.kaveri_bank.account.exception.ResourceNotFound;
import com.kaveri_bank.account.mapper.AccountMapper;
import com.kaveri_bank.account.mapper.CustomerMapper;
import com.kaveri_bank.account.repository.AccountsRepository;
import com.kaveri_bank.account.repository.CustomerRepository;
import com.kaveri_bank.account.service.IAccountService;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountsRepository accountRepository;

    private CustomerRepository customerRepository;
    
    

    @Override
    public void createAccount(CustomerDto dto) {
        if(customerRepository.findByMobileNumber(dto.getMobileNumber()).isPresent()){
            throw new CustomerAlredyExist("Account is alredy created using this Mobile number , plz use a different MobileNumber");
        }
        Customer customer = CustomerMapper.toEntity(dto);
        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy("SYSTEM");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts account = createNewAccount(savedCustomer);
        accountRepository.save(account);
    }

    private Accounts createNewAccount(Customer customer){
        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100000000000l + new Random().nextInt(900000000);

        account.setAccountNumber(randomAccNumber);
        account.setAccountType(Constants.SAVINGS);
        account.setBranchAddress(Constants.ADDRESS);
        account.setCreatedAt(LocalDate.now());
        account.setCreatedBy("SYSTEM");
        return account;
    } 

    @Override
    public CustomerDto getAccount(String mobileNumber){

        Optional<Customer> customer = customerRepository.findByMobileNumber(mobileNumber);
        CustomerDto cus = null;
        if(!customer.isPresent()){
            throw new ResourceNotFound("There is no CUstomer for this Mobile number , plz rigester your MobileNumber");
        }else{
            cus = CustomerMapper.toCustomerDto(customer.get());
        }
        Accounts account = accountRepository.findByCustomerId(customer.get().getCustomerId()).orElseThrow(
            () ->  new ResourceNotFound("There is no account for this CustomerId , plz rigester your MobileNumber"));

        AccountsDto dto = AccountMapper.toAccountDto(account);
        cus.setAccountDto(dto);
        return cus;
    }

    @Override
    public void updateDto(CustomerDto dto) {
        Customer cus = customerRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(
            ()-> new ResourceNotFound("There is no CUstomer for this Mobile number , plz rigester your MobileNumber")
        );

        Accounts account = accountRepository.findByCustomerId(cus.getCustomerId()).orElseThrow(
            ()->  new ResourceNotFound("There is no account for this CustomerId , plz rigester your MobileNumber")
            );
     
        if(!dto.getEmail().isBlank()){
            cus.setEmail(dto.getEmail());
        }
        if(!dto.getName().isBlank()){
            cus.setName(dto.getName());
        }
        if(!dto.getAccountDto().getAccountType().isBlank()){
            account.setAccountType(dto.getAccountDto().getAccountType());
        }
        if(!dto.getAccountDto().getBranchAddress().isBlank()){
            account.setBranchAddress(dto.getAccountDto().getBranchAddress());
        }
        customerRepository.save(cus);

        accountRepository.save(account);
        

    }

    @Override
    public void deleteAccount(String mobileNumber) {

        Customer cus = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFound("Account not found for this mobile number")
            );
        accountRepository.deleteByCustomerId(cus.getCustomerId());
        customerRepository.deleteById(cus.getCustomerId());
    }
    
}
