package com.kaveri_bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaveri_bank.account.entity.Accounts;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
    
}
