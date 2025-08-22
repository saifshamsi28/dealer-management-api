package com.saif.dealer.dto;

import com.saif.dealer.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    private Long paymentId;
    private BigDecimal amount;
    private String dealerName;
    private String dealerEmail;
    private Payment.PaymentMethod method;
    private Payment.PaymentStatus status;
    private LocalDateTime createdAt;

    public PaymentResponse(Long paymentId, BigDecimal amount, String dealerName, String dealerEmail,
                           Payment.PaymentMethod method, Payment.PaymentStatus status,LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.dealerName = dealerName;
        this.dealerEmail = dealerEmail;
        this.method=method;
        this.status=status;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDealerName() { return dealerName; }
    public void setDealerName(String dealerName) { this.dealerName = dealerName; }
    public String getDealerEmail() { return dealerEmail; }
    public void setDealerEmail(String dealerEmail) { this.dealerEmail = dealerEmail; }

    public Payment.PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(Payment.PaymentMethod method) {
        this.method = method;
    }

    public Payment.PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(Payment.PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
