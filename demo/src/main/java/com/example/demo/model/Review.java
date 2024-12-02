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
@Table(name = "reviews")
@SequenceGenerator(name = "review_seq", sequenceName = "review_seq", allocationSize = 1)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Positive
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @NotNull
    @Column(name = "comment", nullable = false)
    private String comment;
}
