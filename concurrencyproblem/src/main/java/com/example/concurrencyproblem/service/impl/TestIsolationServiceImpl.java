package com.example.concurrencyproblem.service.impl;

import com.example.concurrencyproblem.entity.BankAccount;
import com.example.concurrencyproblem.repository.BankAccountRepository;
import com.example.concurrencyproblem.service.TestIsolationService;
import com.example.concurrencyproblem.service.spec.TestIsolationServiceSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestIsolationServiceImpl implements TestIsolationService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TestIsolationServiceSpec.CountAllResponse countAllAccount(TestIsolationServiceSpec.CountAllRequest request) {

        log.info("Start : count method");

        Long count1 = bankAccountRepository.getNoOfAccount();
        log.info("count1 = {}", count1);

        waitingTime(request.getWaitingTime());
        log.info("After waiting");

        Long count2 = bankAccountRepository.getNoOfAccount();
        log.info("count2 = {}", count2);

        return new TestIsolationServiceSpec.CountAllResponse()
                .setCount1(count1)
                .setCount2(count2);

    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public TestIsolationServiceSpec.ReadResponse read(TestIsolationServiceSpec.ReadRequest request) {

        log.info("Start : read method");

        TestIsolationServiceSpec.BankAccountInfo read1 = bankAccountRepository.findByBankAccountIdForTest(request.getBankAccountId()).orElse(null);
        log.info("read1 = {}", read1);

        waitingTime(request.getWaitingTime());
        log.info("After waiting");

        TestIsolationServiceSpec.BankAccountInfo read2 = bankAccountRepository.findByBankAccountIdForTest(request.getBankAccountId()).orElse(null);
        log.info("read2 = {}", read2);

        return new TestIsolationServiceSpec.ReadResponse()
                .setRead1(read1)
                .setRead2(read2);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public TestIsolationServiceSpec.BankAccountInfo write(TestIsolationServiceSpec.WriteRequest request) {

        log.info("Start : write method");

        BankAccount bankAccount = bankAccountRepository.findById(request.getBankAccountId())
                       .orElse(new BankAccount().setBankAccountId(request.getBankAccountId()).setAmount(request.getAmount()));

        bankAccount.setAmount(request.getAmount());

        bankAccount = bankAccountRepository.saveAndFlush(bankAccount);
        waitingTime(10);
        log.info("write method : After waiting time ");

        if (bankAccount.getAmount().equals(BigDecimal.valueOf(13))) {
            log.info("In throw error 13");
            throw new RuntimeException("Amount equal 13");
        }

        log.info("Before : write method end : commit");
        return new TestIsolationServiceSpec.BankAccountInfo()
                .setAmount(bankAccount.getAmount())
                .setBankAccountId(bankAccount.getBankAccountId());
    }


    protected void waitingTime(int second) {

        try {
            Thread.sleep( second * 1000L);
        } catch (InterruptedException e) {
            log.warn("Thread has InterruptedException ", e);
            Thread.currentThread().interrupt();
        }
    }

}
