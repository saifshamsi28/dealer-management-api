package com.saif.dealer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Reference;

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
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ManyToOne relation for JPQL join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dealer_id", insertable = false, updatable = false)
    private Dealer dealer;

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
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public enum PaymentMethod {
        UPI, CARD, NETBANKING
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }
}
