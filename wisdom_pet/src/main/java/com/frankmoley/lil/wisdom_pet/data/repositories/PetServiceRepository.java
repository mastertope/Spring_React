package com.frankmoley.lil.wisdom_pet.data.repositories;

import com.frankmoley.lil.wisdom_pet.data.entities.PetServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PetServiceRepository extends CrudRepository<PetServiceEntity, Long> {
    PetServiceEntity findByName(String name);
}
