package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "shopping_carts")
@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @NotNull
    @Positive
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
}
