package com.example.concurrencyproblem.service.spec;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankServiceSpec {


    @Accessors(chain = true)
    @Data
    public static class WithdrawRequest {
        private String bankAccountId;
        private BigDecimal amount;
    }


    @Accessors(chain = true)
    @Data
    public static class VerifyLoanRequest {
        private String bankAccountId;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    @Data
    public static class BankAccountInfo {
        private String bankAccountId;
        private BigDecimal amount;
    }


    @Accessors(chain = true)
    @Data
    public static class VerifyLoanResponse {
        private boolean loanResult;
        private BankAccountInfo bankAccountInfo;
        private Date createAt;
    }


    @Accessors(chain = true)
    @Data
    public static class SummaryActiveAccountReportResponse {
        private Long noAccount;
        private BigDecimal sumAmount;
    }


    @Accessors(chain = true)
    @Data
    public static class AddAccountRequest {
        private String bankAccountId;
        private BigDecimal amount;
    }
}
