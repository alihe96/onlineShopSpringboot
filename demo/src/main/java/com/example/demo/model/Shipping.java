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
@Table(name = "shippings")
@SequenceGenerator(name = "shipping_seq", sequenceName = "shipping_seq", allocationSize = 1)
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "shipping_date", nullable = false)
    private String shippingDate;

    @NotNull
    @Column(name = "shipping_method", nullable = false)
    private String shippingMethod;

    @NotNull
    @Positive
    @Column(name = "shipping_cost", nullable = false)
    private Double shippingCost;

    @NotNull
    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @NotNull
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name = "estimated_delivery_date")
    private String estimatedDeliveryDate;

    @Column(name = "actual_delivery_date")
    private String actualDeliveryDate;
}
