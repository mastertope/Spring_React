package com.frankmoley.lil.wisdom_pet.services;

import com.frankmoley.lil.wisdom_pet.data.entities.PetServiceEntity;
import com.frankmoley.lil.wisdom_pet.data.repositories.PetServiceRepository;
import com.frankmoley.lil.wisdom_pet.web.errors.NotFoundException;
import com.frankmoley.lil.wisdom_pet.web.models.PetService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceService {
    private final PetServiceRepository petServiceRepository;

    public PetServiceService(PetServiceRepository petServiceRepository) {
        this.petServiceRepository = petServiceRepository;
    }

    public List<PetService> getAllPetServices(String filterByName) {
        List<PetService> petServices = new ArrayList<>();
        if (StringUtils.hasLength(filterByName)) {
            PetServiceEntity entity = this.petServiceRepository.findByName(filterByName);
            petServices.add(translateDbToWeb(entity));
        } else {
            Iterable<PetServiceEntity> entities = this.petServiceRepository.findAll();
            entities.forEach(entity-> petServices.add(translateDbToWeb(entity)));
        }
        return petServices;
    }

    public PetService getPetService(long id) {
        Optional<PetServiceEntity> petServiceEntityOptional = this.petServiceRepository.findById(id);
        if (petServiceEntityOptional.isEmpty()){
            throw new NotFoundException("pet service not found with id");
        }
        return this.translateDbToWeb(petServiceEntityOptional.get());
    }

    public PetService createOrUpdatePetService (PetService petService){
        PetServiceEntity entity = this.translateWebToDb(petService);
        entity = this.petServiceRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deletePetService(long id) {
        this.petServiceRepository.deleteById(id);
    }

    private PetServiceEntity translateWebToDb (PetService petService) {
        PetServiceEntity entity = new PetServiceEntity();
        entity.setId(petService.getServiceId());
        entity.setName(petService.getName());
        entity.setPrice(petService.getPrice());

        return entity;
    }

    private PetService translateDbToWeb (PetServiceEntity entity) {
        return new PetService(entity.getId(), entity.getName(), entity.getPrice());
    }
}
