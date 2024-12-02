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
@Table(name = "categories")
@SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
}
