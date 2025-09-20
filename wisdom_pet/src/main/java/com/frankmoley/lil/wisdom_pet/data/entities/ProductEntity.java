package com.frankmoley.lil.wisdom_pet.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name="PRODUCTS")
@Data
@ToString
public class ProductEntity {
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private BigDecimal price;

    @Column(name="VENDOR_ID")
    private long vendorId;
}
