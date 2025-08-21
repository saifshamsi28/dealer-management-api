package com.saif.dealer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Dealer ID is required")
    @Column(name = "dealer_id", nullable = false)
    private Long dealerId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment method is required")
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private String createdAt = String.valueOf(System.currentTimeMillis());

    @Column(name = "updated_at")
    private String updatedAt;

    public Payment() {}

    public Payment(Long dealerId, BigDecimal amount, PaymentMethod method) {
        this.dealerId = dealerId;
        this.amount = amount;
        this.method = method;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDealerId() { return dealerId; }
    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { 
        this.status = status;
        this.updatedAt = String.valueOf(System.currentTimeMillis());
    }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = String.valueOf(createdAt); }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = String.valueOf(updatedAt); }

    public enum PaymentMethod {
        UPI, CARD, NETBANKING
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }
}
