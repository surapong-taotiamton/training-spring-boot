package com.example.dependencyinjection;

import com.example.dependencyinjection.service.PaymentService;

import java.math.BigDecimal;

public class FortuneTeller {



    private PaymentService paymentService;

    public FortuneTeller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }



    public String createPayment() {
        return paymentService.createPayment("FORTUNE_101", BigDecimal.valueOf(100));
    }

    public String createPaymentWithPaymentService(PaymentService injectPaymentService) {
        return injectPaymentService.createPayment("FORTUNE_101", BigDecimal.valueOf(100));
    }

    public int mobileNoFortune(String paymentId, String mobileNo) {
        if (paymentService.paymentComplete(paymentId)) {
            return calculateLuck(mobileNo);
        } else {
            throw new RuntimeException("Payment not complete");
        }
    }

    private int calculateLuck(String mobileNo) {
        int sum = 0;
        for(int i = 0; i < mobileNo.length(); i++) {
            sum += Integer.parseInt(mobileNo.charAt(i) + "");
        }
        return sum % 10;
    }


    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
