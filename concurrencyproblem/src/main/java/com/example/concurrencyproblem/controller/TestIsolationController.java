package com.example.concurrencyproblem.controller;

import com.example.concurrencyproblem.service.TestIsolationService;
import com.example.concurrencyproblem.service.spec.TestIsolationServiceSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestIsolationController {

    private final TestIsolationService testIsolationService;

    @PostMapping("/test-isolation/read")
    public TestIsolationServiceSpec.ReadResponse read(@RequestBody TestIsolationServiceSpec.ReadRequest request) {
        return testIsolationService.read(request);
    }

    @PostMapping("/test-isolation/write")
    public TestIsolationServiceSpec.BankAccountInfo read(@RequestBody TestIsolationServiceSpec.WriteRequest request) {
        return testIsolationService.write(request);
    }

    @PostMapping("/test-isolation/count")
    public TestIsolationServiceSpec.CountAllResponse count(@RequestBody TestIsolationServiceSpec.CountAllRequest request) {
        return testIsolationService.countAllAccount(request);
    }

}
