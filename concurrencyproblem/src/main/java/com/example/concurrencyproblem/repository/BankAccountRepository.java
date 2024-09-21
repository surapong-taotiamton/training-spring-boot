package com.example.concurrencyproblem.repository;

import com.example.concurrencyproblem.entity.BankAccount;
import com.example.concurrencyproblem.service.spec.BankServiceSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    @Query("SELECT new com.example.concurrencyproblem.service.spec.BankServiceSpec$BankAccountInfo(t.bankAccountId , t.amount) t FROM BankAccount t WHERE t.bankAccountId = :bankAccountId")
    Optional<BankServiceSpec.BankAccountInfo> findByBankAccountIdForVerify(@Param("bankAccountId") String bankAccountId);

    @Query("SELECT count(t)  FROM BankAccount t")
    Long getNoOfAccount();

    @Query("SELECT sum(t.amount)  FROM BankAccount t")
    BigDecimal sumOfAmountInSystem();

}
