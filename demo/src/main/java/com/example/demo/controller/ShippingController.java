package com.example.demo.controller;

import com.example.demo.model.Shipping;
import com.example.demo.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    // Create a new shipping
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        Shipping savedShipping = shippingService.saveShipping(shipping);
        return ResponseEntity.ok(savedShipping);
    }

    // Get all shippings
    @GetMapping
    public List<Shipping> getAllShippings() {
        return shippingService.findAllShippings();
    }

    // Get a shipping by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable Long id) {
        Optional<Shipping> shipping = shippingService.findShippingById(id);
        return shipping.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a shipping by ID
    @PutMapping("/{id}")
    public ResponseEntity<Shipping> updateShipping(@PathVariable Long id, @RequestBody Shipping updatedShipping) {
        Optional<Shipping> updated = shippingService.updateShipping(id, updatedShipping);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a shipping by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        shippingService.deleteShipping(id);
        return ResponseEntity.noContent().build();
    }
}
