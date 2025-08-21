package com.saif.dealer.controller;

import com.saif.dealer.entity.Dealer;
import com.saif.dealer.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealers")
@CrossOrigin
public class DealerController {

    @Autowired
    private DealerRepository dealerRepository;

    @GetMapping
    public List<Dealer> getAllDealers() {
        return dealerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        Optional<Dealer> dealer = dealerRepository.findById(id);
        return dealer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createDealer(@Valid @RequestBody Dealer dealer) {
        if (dealerRepository.existsByEmail(dealer.getEmail())) {
            return new ResponseEntity<>("Dealer already exists: "+dealer.getEmail()+"\nTry another email"
                    , HttpStatus.CONFLICT);
        }
        Dealer savedDealer = dealerRepository.save(dealer);
        return ResponseEntity.ok(savedDealer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Long id, @Valid @RequestBody Dealer dealerDetails) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(id);
        
        if (optionalDealer.isPresent()) {
            Dealer dealer = optionalDealer.get();
            dealer.setName(dealerDetails.getName());
            dealer.setEmail(dealerDetails.getEmail());
            dealer.setSubscriptionType(dealerDetails.getSubscriptionType());
            
            Dealer updatedDealer = dealerRepository.save(dealer);
            return ResponseEntity.ok(updatedDealer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDealer(@PathVariable Long id) {
        if (dealerRepository.existsById(id)) {
            dealerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/premium")
    public List<Dealer> getPremiumDealers() {
        return dealerRepository.findPremiumDealers();
    }
}
