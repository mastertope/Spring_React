package com.frankmoley.lil.wisdom_pet.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {
    public Long vendorId;
    public String name;
    public String contact;
    public String emailAddress;
    public String phoneNumber;
    public String address;
}
