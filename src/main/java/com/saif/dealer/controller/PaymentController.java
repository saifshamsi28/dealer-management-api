package com.saif.dealer.controller;

import com.saif.dealer.dto.PaymentRequest;
import com.saif.dealer.entity.Payment;
import com.saif.dealer.repository.PaymentRepository;
import com.saif.dealer.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        Payment payment = new Payment(
            paymentRequest.getDealerId(),
            paymentRequest.getAmount(),
            paymentRequest.getMethod()
        );
        
        Payment initiatedPayment = paymentService.initiatePayment(payment);
        return ResponseEntity.ok(initiatedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/dealer/{dealerId}")
    public List<Payment> getPaymentsByDealer(@PathVariable Long dealerId) {
        return paymentRepository.findByDealerId(dealerId);
    }

    @GetMapping("/status/{status}")
    public List<Payment> getPaymentsByStatus(@PathVariable Payment.PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> deletePaymentByID(@PathVariable Long id){
        System.out.println("deleting payment with id : "+id);
        Payment payment = paymentService.deletePaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }
}
