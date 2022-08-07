package com.projeto.hrpayroll.services;

import com.projeto.hrpayroll.entities.Payment;
import com.projeto.hrpayroll.entities.Worker;
import com.projeto.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days){
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
    public Payment getPaymentFallback(long workerId, int days, String error){
        return Payment.builder()
                .name("Fallback-error workerId: " + workerId + " " + error)
                .dailyIncome(0.0)
                .days(days)
                .build();
    }
}
