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
@Table(name = "payments")
@SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", allocationSize = 1)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @NotNull
    @Positive
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;
}
