package com.example.demo.service;

import com.example.demo.model.Coupon;
import com.example.demo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Transactional
    public Coupon updateCoupon(Long id, Coupon couponDetails) {
        return couponRepository.findById(id).map(coupon -> {
            coupon.setCode(couponDetails.getCode());
            coupon.setDiscountPercentage(couponDetails.getDiscountPercentage());
            coupon.setValidFrom(couponDetails.getValidFrom());
            coupon.setValidUntil(couponDetails.getValidUntil());
            coupon.setStatus(couponDetails.getStatus());
            coupon.setMinPurchaseAmount(couponDetails.getMinPurchaseAmount());
            coupon.setUser(couponDetails.getUser());
            return couponRepository.save(coupon);
        }).orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Optional<Coupon> getCouponById(Long id) {
        return couponRepository.findById(id);
    }

    public Optional<Coupon> getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }
}
