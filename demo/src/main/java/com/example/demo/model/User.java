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
@Table(name = "users")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @NotNull
    @Column(name = "billing_address", nullable = false)
    private String billingAddress;
}
