package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "coupons")
@SequenceGenerator(name = "coupon_seq", sequenceName = "coupon_seq", allocationSize = 1)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Positive
    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;

    @NotNull
    @Column(name = "valid_from", nullable = false)
    private String validFrom;

    @NotNull
    @Column(name = "valid_until", nullable = false)
    private String validUntil;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status; // (e.g., "active", "expired")

    @NotNull
    @Column(name = "min_purchase_amount", nullable = false)
    private Double minPurchaseAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  // If the coupon is associated with a specific user
}
