package com.frankmoley.lil.wisdom_pet.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="VENDORS")
@Data
@ToString
public class VendorEntity {
    @Id
    @Column(name="VENDOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CONTACT")
    private String contact;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ADDRESS")
    private String address;
}
