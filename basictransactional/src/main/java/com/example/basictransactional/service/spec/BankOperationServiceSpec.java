package com.example.basictransactional.service.spec;

import com.example.basictransactional.entity.BankAccount;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankOperationServiceSpec {

    @Accessors(chain = true)
    @Data
    public static class BankAccountInfo {
        private String bankAccountId;
        private BigDecimal amount;
    }

    @Accessors(chain = true)
    @Data
    public static class TransferRequest {
        private String fromAccountId;
        private String toAccountId;
        private BigDecimal amount;
    }


    @Accessors(chain = true)
    @Data
    public static class BatchTransferRequest {
        private List<TransferRequest> transferRequestList;
    }

    @Accessors(chain = true)
    @Data
    public static class BatchTransferResult {
        private TransferRequest transferRequest;
        private boolean result;
    }

    @Accessors(chain = true)
    @Data
    public static class TransferResultInformation {
        private BankAccountInfo fromAccount;
        private BankAccountInfo toAccount;
    }

}
