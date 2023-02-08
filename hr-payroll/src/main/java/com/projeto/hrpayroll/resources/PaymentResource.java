package com.projeto.hrpayroll.resources;

import com.projeto.hrpayroll.entities.Payment;
import com.projeto.hrpayroll.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
    @Autowired
    private PaymentService service;

    @CircuitBreaker(name = "workerCB", fallbackMethod = "getPaymentFallback")
    @GetMapping(value = "{workerId}/days/{workedDays}")
    public ResponseEntity<Payment> getPayment(@PathVariable long workerId, @PathVariable Integer workedDays){
        return ResponseEntity.ok().body(service.getPayment(workerId, workedDays));
    }

    public ResponseEntity<Payment> getPaymentFallback(long workerId, Integer workedDays, Exception e){
        return ResponseEntity.ok().body(service.getPaymentFallback(workerId, workedDays, e.getMessage()));
    }
}
