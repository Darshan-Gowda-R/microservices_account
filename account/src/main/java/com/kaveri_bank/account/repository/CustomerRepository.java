package com.kaveri_bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaveri_bank.account.entity.Customer;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findByMobileNumber(String monNumber);
    
}
