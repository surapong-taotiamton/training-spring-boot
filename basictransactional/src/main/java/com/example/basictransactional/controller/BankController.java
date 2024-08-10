package com.example.basictransactional.controller;

import com.example.basictransactional.service.BankOperationService;
import com.example.basictransactional.service.spec.BankOperationServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BankController {

    private final BankOperationService bankOperationService;

    @PostMapping("/transfer")
    public BankOperationServiceSpec.TransferResultInformation transfer(
            @RequestBody BankOperationServiceSpec.TransferRequest request
            ) throws Exception {
        return bankOperationService.transferMoney(request);
    }

    @PostMapping("/batch")
    public List<BankOperationServiceSpec.BatchTransferResult> batchTransfer(
            @RequestBody BankOperationServiceSpec.BatchTransferRequest request
    ) throws Exception {
        return bankOperationService.batchTransfer(request);
    }

    @PostMapping("/newbatch")
    public List<BankOperationServiceSpec.BatchTransferResult> newBatchTransfer(
            @RequestBody BankOperationServiceSpec.BatchTransferRequest request
    ) throws Exception {
        return bankOperationService.newBatchTransfer(request);
    }

}
