package com.example.concurrencyproblem.service;

import com.example.concurrencyproblem.service.spec.TestIsolationServiceSpec;

public interface TestIsolationService {

    TestIsolationServiceSpec.ReadResponse read(TestIsolationServiceSpec.ReadRequest request);
    TestIsolationServiceSpec.BankAccountInfo write(TestIsolationServiceSpec.WriteRequest request);
    TestIsolationServiceSpec.CountAllResponse countAllAccount(TestIsolationServiceSpec.CountAllRequest request);

}
