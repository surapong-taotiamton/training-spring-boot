package com.example.dependencyinjection;

import com.example.dependencyinjection.service.PaymentService;

public class ExampleUse {

    public static void main(String[] args) {

        FortuneTeller fortuneTeller = new FortuneTeller( new PaymentService("NORMAL"));
        String paymentId = fortuneTeller.createPayment();

        // GOTO pay this paymentId

        int luck = fortuneTeller.mobileNoFortune(paymentId, "0811234567");
        System.out.println("Luck is : " + luck);

    }

}
