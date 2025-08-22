package com.saif.dealer.repository;

import com.saif.dealer.dto.PaymentResponse;
import com.saif.dealer.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDealerId(Long dealerId);
    List<Payment> findByStatus(Payment.PaymentStatus status);

    @Query("SELECT new com.saif.dealer.dto.PaymentResponse(" +
            "p.id, p.amount, d.name, d.email, p.method, p.status, p.createdAt) " +
            "FROM Payment p JOIN p.dealer d")
    List<PaymentResponse> findAllWithDealerInfo();
}
