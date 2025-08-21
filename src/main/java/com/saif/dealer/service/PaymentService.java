package com.saif.dealer.service;

import com.saif.dealer.entity.Payment;
import com.saif.dealer.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment initiatePayment(Payment payment) {
        payment.setStatus(Payment.PaymentStatus.PENDING);
        Payment savedPayment = paymentRepository.save(payment);
        
        // Simulating the payment processing asynchronously
        processPaymentAsync(savedPayment.getId());
        
        return savedPayment;
    }

    @Async
    public CompletableFuture<Void> processPaymentAsync(Long paymentId) {
        try {
            // Simulating for 5 seconds processing time as mentioned in the requirement
            Thread.sleep(5000);
            
            Payment payment = paymentRepository.findById(paymentId).orElse(null);
            if (payment != null) {
                payment.setStatus(Payment.PaymentStatus.SUCCESS);
                paymentRepository.save(payment);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return CompletableFuture.completedFuture(null);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment deletePaymentById(Long id){
        Payment payment=paymentRepository.findById(id).orElse(null);
        if ((payment!=null)){
            paymentRepository.deleteById(id);
        }
        return payment;
    }
}
