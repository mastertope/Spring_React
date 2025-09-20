package com.frankmoley.lil.wisdom_pet.data.repositories;

import com.frankmoley.lil.wisdom_pet.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByName(String name);
}
