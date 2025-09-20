package com.frankmoley.lil.wisdom_pet.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name="PET_SERVICES")
@Data
@ToString
public class PetServiceEntity {
    @Id
    @Column(name="SERVICE_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private BigDecimal price;
}
