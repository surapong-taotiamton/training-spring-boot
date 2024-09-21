package com.example.concurrencyproblem.controller;

import com.example.concurrencyproblem.service.BankService;
import com.example.concurrencyproblem.service.spec.BankServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BankAccountController {

    private final BankService bankService;

    @PostMapping("/bank-service/withdraw")
    public BankServiceSpec.BankAccountInfo withdraw(
            @RequestBody BankServiceSpec.WithdrawRequest withdrawRequest
            ) {
        return bankService.withdraw(withdrawRequest);
    }

    @PostMapping("/bank-service/verify-loan")
    public BankServiceSpec.VerifyLoanResponse verifyLoan(
            @RequestBody BankServiceSpec.VerifyLoanRequest verifyLoanRequest
    ) {
        return bankService.verifyLoan(verifyLoanRequest);
    }


        @PostMapping("/bank-service/add-account")
    public BankServiceSpec.BankAccountInfo addAccount(
            @RequestBody BankServiceSpec.AddAccountRequest addAccountRequest
    ) {
        return bankService.addAccount(addAccountRequest);
    }

    @PostMapping("/bank-service/summary-active-account-report")
    public BankServiceSpec.SummaryActiveAccountReportResponse summaryActiveAccountReport() {
        return bankService.summaryActiveAccountReport();
    }


}
