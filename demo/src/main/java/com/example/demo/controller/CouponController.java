package com.example.demo.controller;

import com.example.demo.model.Coupon;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // Create a new coupon
    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon createdCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createdCoupon);
    }

    // Get all coupons
    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    // Get coupon by ID
    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Long id) {
        Optional<Coupon> coupon = couponService.getCouponById(id);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get coupon by code
    @GetMapping("/code/{code}")
    public ResponseEntity<Coupon> getCouponByCode(@PathVariable String code) {
        Optional<Coupon> coupon = couponService.getCouponByCode(code);
        return coupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update coupon by ID
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Long id, @RequestBody Coupon couponDetails) {
        Optional<Coupon> updatedCoupon = Optional.ofNullable(couponService.updateCoupon(id, couponDetails));
        return updatedCoupon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete coupon by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}
