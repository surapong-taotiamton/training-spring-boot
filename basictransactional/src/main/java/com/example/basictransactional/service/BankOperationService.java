package com.example.basictransactional.service;

import com.example.basictransactional.entity.BankAccount;
import com.example.basictransactional.repository.BankAccountRepository;
import com.example.basictransactional.service.spec.BankOperationServiceSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankOperationService {
    private final BankAccountRepository bankAccountRepository;
    private final PlatformTransactionManager platformTransactionManager;


    @Transactional
    public List<BankOperationServiceSpec.BatchTransferResult> batchTransfer(BankOperationServiceSpec.BatchTransferRequest batchTransferRequest) throws Exception {
        List<BankOperationServiceSpec.BatchTransferResult> batchTransferResults = new ArrayList<>();

        for (BankOperationServiceSpec.TransferRequest transferRequest : batchTransferRequest.getTransferRequestList()) {
            boolean result;
            try {
                this.transferMoney(transferRequest);
                result = true;
            } catch (Exception ex) {
                result = false;
                throw new RuntimeException(ex);
            }
            log.info("{} : {}", transferRequest, result);
            batchTransferResults.add(new BankOperationServiceSpec.BatchTransferResult().setResult(result).setTransferRequest(transferRequest));
        }
        return batchTransferResults;
    }


    @Transactional(rollbackFor = { Exception.class })
    public BankOperationServiceSpec.TransferResultInformation transferMoney(BankOperationServiceSpec.TransferRequest transferRequest) throws Exception {

        String fromAccountId = transferRequest.getFromAccountId();
        String toAccountId = transferRequest.getToAccountId();
        BigDecimal amount = transferRequest.getAmount();

        BankAccount fromBankAccount = bankAccountRepository.findById(fromAccountId).orElseThrow();
        BankAccount toBankAccount = bankAccountRepository.findById(toAccountId).orElseThrow();

        BigDecimal newAmountOfFromAccount = fromBankAccount.getAmount().subtract(amount);
        fromBankAccount.setAmount(newAmountOfFromAccount);
        fromBankAccount = bankAccountRepository.saveAndFlush(fromBankAccount);

        log.info("########################## {} : Save from account amount = {} ", fromAccountId, fromBankAccount.getAmount() );


        if (amount.equals(BigDecimal.valueOf(13))) {
            throw new RuntimeException("Error because amount is 13");
        }

        if (amount.equals(BigDecimal.valueOf(23))) {
            throw new Exception("Error because amount is 23");
        }


        BigDecimal newAmountOfToAccount = toBankAccount.getAmount().add(amount);
        toBankAccount.setAmount(newAmountOfToAccount);
        toBankAccount = bankAccountRepository.saveAndFlush(toBankAccount);

        log.info("########################## {} : Save to account amount = {} ", toAccountId, toBankAccount.getAmount() );

        BankOperationServiceSpec.BankAccountInfo fromAccountInfo = new BankOperationServiceSpec.BankAccountInfo()
                .setBankAccountId(fromBankAccount.getBankAccountId())
                .setAmount(fromBankAccount.getAmount());

        BankOperationServiceSpec.BankAccountInfo toAccountInfo = new BankOperationServiceSpec.BankAccountInfo()
                .setBankAccountId(toBankAccount.getBankAccountId())
                .setAmount(toBankAccount.getAmount());

        return new BankOperationServiceSpec.TransferResultInformation()
                .setFromAccount(fromAccountInfo)
                .setToAccount(toAccountInfo);
    }



    public List<BankOperationServiceSpec.BatchTransferResult> newBatchTransfer(BankOperationServiceSpec.BatchTransferRequest batchTransferRequest) throws Exception {
        List<BankOperationServiceSpec.BatchTransferResult> batchTransferResults = new ArrayList<>();

        for (BankOperationServiceSpec.TransferRequest transferRequest : batchTransferRequest.getTransferRequestList()) {
            TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            boolean result;
            try {
                transactionTemplate.execute(status -> {
                    try {
                        return this.transferMoney(transferRequest);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                result = true;
            } catch (Exception ex) {
                result = false;
            }
            log.info("************");
            log.info("************ {} : {}", transferRequest, result);
            batchTransferResults.add(new BankOperationServiceSpec.BatchTransferResult().setResult(result).setTransferRequest(transferRequest));
        }
        return batchTransferResults;
    }


}
