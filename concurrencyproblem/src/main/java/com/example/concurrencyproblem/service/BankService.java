package com.example.concurrencyproblem.service;

import com.example.concurrencyproblem.service.spec.BankServiceSpec;

public interface BankService {

    BankServiceSpec.BankAccountInfo withdraw(BankServiceSpec.WithdrawRequest withdrawRequest);

    BankServiceSpec.VerifyLoanResponse verifyLoan(BankServiceSpec.VerifyLoanRequest verifyLoanRequest);

    BankServiceSpec.SummaryActiveAccountReportResponse summaryActiveAccountReport();

    BankServiceSpec.BankAccountInfo addAccount(BankServiceSpec.AddAccountRequest addAccountRequest);

}
