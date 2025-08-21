package com.saif.dealer.repository;

import com.saif.dealer.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    Optional<Dealer> findByEmail(String email);
    
    @Query("SELECT d FROM Dealer d WHERE d.subscriptionType = 'PREMIUM'")
    List<Dealer> findPremiumDealers();
    
    boolean existsByEmail(String email);
}
