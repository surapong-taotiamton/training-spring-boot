package com.example.concurrencyproblem.service.spec;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestIsolationServiceSpec {

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
    public static class ReadRequest {
        private String bankAccountId;
        private int waitingTime;
    }


    @Accessors(chain = true)
    @Data
    public static class ReadResponse {
        private BankAccountInfo read1;
        private BankAccountInfo read2;
    }


    @Accessors(chain = true)
    @Data
    public static class WriteRequest {
        private String bankAccountId;
        private BigDecimal amount;
        private int waitingTime;
    }


    @Accessors(chain = true)
    @Data
    public static class CountAllRequest {
        private int waitingTime;
    }


    @Accessors(chain = true)
    @Data
    public static class CountAllResponse {
        private long count1;
        private long count2;
    }

}
