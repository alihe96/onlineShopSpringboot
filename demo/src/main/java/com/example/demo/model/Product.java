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
@Table(name = "products")
@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Positive
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Positive
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
