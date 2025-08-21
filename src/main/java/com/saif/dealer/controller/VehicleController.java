package com.saif.dealer.controller;

import com.saif.dealer.entity.Vehicle;
import com.saif.dealer.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @Valid @RequestBody Vehicle vehicleDetails) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setDealerId(vehicleDetails.getDealerId());
            vehicle.setModel(vehicleDetails.getModel());
            vehicle.setPrice(vehicleDetails.getPrice());
            vehicle.setStatus(vehicleDetails.getStatus());
            
            Vehicle updatedVehicle = vehicleRepository.save(vehicle);
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dealer/{dealerId}")
    public List<Vehicle> getVehiclesByDealer(@PathVariable Long dealerId) {
        return vehicleRepository.findByDealerId(dealerId);
    }

    @GetMapping("/premium-dealers")
    public List<Vehicle> getVehiclesByPremiumDealers() {
        return vehicleRepository.findVehiclesByPremiumDealers();
    }

    @GetMapping("/status/{status}")
    public List<Vehicle> getVehiclesByStatus(@PathVariable Vehicle.VehicleStatus status) {
        return vehicleRepository.findByStatus(status);
    }
}
