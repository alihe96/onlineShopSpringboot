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
@Table(name = "orders")
@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Positive
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @NotNull
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
