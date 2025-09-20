package com.frankmoley.lil.wisdom_pet.data.repositories;

import com.frankmoley.lil.wisdom_pet.data.entities.VendorEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<VendorEntity, Long> {
    VendorEntity findByEmail(String email);

}
