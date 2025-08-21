package com.saif.dealer.repository;

import com.saif.dealer.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDealerId(Long dealerId);
    
    @Query("SELECT v FROM Vehicle v JOIN v.dealer d WHERE d.subscriptionType = 'PREMIUM'")
    List<Vehicle> findVehiclesByPremiumDealers();
    
    List<Vehicle> findByStatus(Vehicle.VehicleStatus status);
}
