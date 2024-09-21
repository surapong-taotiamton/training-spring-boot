package com.example.concurrencyproblem.service.impl;

import com.example.concurrencyproblem.entity.BankAccount;
import com.example.concurrencyproblem.repository.BankAccountRepository;
import com.example.concurrencyproblem.service.BankService;
import com.example.concurrencyproblem.service.spec.BankServiceSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class BankServiceImpl implements BankService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public BankServiceSpec.BankAccountInfo withdraw(BankServiceSpec.WithdrawRequest withdrawRequest) {

        BankAccount bankAccount = bankAccountRepository.findById(withdrawRequest.getBankAccountId()).orElseThrow();
        log.info("### BankAccountInfo After Read :  bankAccountId : {}  amount : {}", bankAccount.getBankAccountId(), bankAccount.getAmount());

        waitingTime(10);

        log.info("after waiting time After Read");

        BigDecimal newAmount = bankAccount.getAmount().subtract(withdrawRequest.getAmount());
        bankAccount.setAmount(newAmount);

        bankAccount = bankAccountRepository.saveAndFlush(bankAccount);
        log.info("### BankAccountInfo After Write :  bankAccountId : {}  amount : {}", bankAccount.getBankAccountId(), bankAccount.getAmount());

        waitingTime(10);

        log.info("after waiting time After Write");

        if (withdrawRequest.getAmount().equals( BigDecimal.valueOf(13L) )) {
            throw new RuntimeException("Error Because Amount is 13");
        }


        return new BankServiceSpec.BankAccountInfo()
                .setAmount(bankAccount.getAmount())
                .setBankAccountId(bankAccount.getBankAccountId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public BankServiceSpec.VerifyLoanResponse verifyLoan(BankServiceSpec.VerifyLoanRequest verifyLoanRequest) {

        BankServiceSpec.BankAccountInfo bankAccountInfo = bankAccountRepository.findByBankAccountIdForVerify(verifyLoanRequest.getBankAccountId()).orElseThrow();

        if ( bankAccountInfo.getAmount().compareTo(BigDecimal.valueOf(50L)) < 0  ) {
            throw new RuntimeException("Can not loan because account less than 50  ");
        }

        log.info("#### After check can loan");

        waitingTime(20);

        log.info("#### After waiting time");

        return createVerifyLoanResponse(verifyLoanRequest.getBankAccountId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public BankServiceSpec.SummaryActiveAccountReportResponse summaryActiveAccountReport() {

        Long noAccount = bankAccountRepository.getNoOfAccount();
        waitingTime(20);
        BigDecimal sumOfAmount = bankAccountRepository.sumOfAmountInSystem();
        return new BankServiceSpec.SummaryActiveAccountReportResponse()
                .setNoAccount(noAccount)
                .setSumAmount(sumOfAmount);
    }

    @Override
    public BankServiceSpec.BankAccountInfo addAccount(BankServiceSpec.AddAccountRequest addAccountRequest) {


        BankAccount bankAccount = bankAccountRepository.save(
                new BankAccount().setBankAccountId(addAccountRequest.getBankAccountId())
                        .setAmount(addAccountRequest.getAmount())
        );

        return new BankServiceSpec.BankAccountInfo()
                .setBankAccountId(bankAccount.getBankAccountId())
                .setAmount(bankAccount.getAmount());
    }

    protected BankServiceSpec.VerifyLoanResponse createVerifyLoanResponse(String bankAccountId) {
        BankServiceSpec.BankAccountInfo bankAccountInfo = bankAccountRepository.findByBankAccountIdForVerify(bankAccountId).orElseThrow();

        return new BankServiceSpec.VerifyLoanResponse()
                .setLoanResult(true)
                .setBankAccountInfo(bankAccountInfo)
                .setCreateAt(new Date());
    }

    public void waitingTime(int second) {

        try {
            Thread.sleep( second * 1000L);
        } catch (InterruptedException e) {
            log.warn("Thread has InterruptedException ", e);
            Thread.currentThread().interrupt();
        }
    }
}
