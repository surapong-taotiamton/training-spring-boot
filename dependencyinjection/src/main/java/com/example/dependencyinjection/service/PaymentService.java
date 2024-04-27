package com.example.dependencyinjection.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class PaymentService {

    private String type;

    public PaymentService(String type) {
        this.type = type;
    }

    public String createPayment(String userId, BigDecimal amount) {

        String paymentId = "PAT101";

        if (type.equals("VIP")) {
            return String.format("%s-%s", userId, paymentId) ;
        } else {
            if (LocalDateTime.now().isBefore( LocalDateTime.now().withHour(23).withMinute(0).withSecond(0))) {
                return String.format("%s-%s", userId, paymentId) ;
            } else {
                throw new RuntimeException("Out of service time");
            }
        }
    }

    public boolean paymentComplete(String paymentId) {
        // Example for explain flow
        return paymentId.endsWith("PAT101");
    }

}
