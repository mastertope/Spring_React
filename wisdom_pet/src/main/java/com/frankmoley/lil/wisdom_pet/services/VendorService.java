package com.frankmoley.lil.wisdom_pet.services;

import com.frankmoley.lil.wisdom_pet.data.entities.VendorEntity;
import com.frankmoley.lil.wisdom_pet.data.repositories.VendorRepository;
import com.frankmoley.lil.wisdom_pet.web.errors.NotFoundException;
import com.frankmoley.lil.wisdom_pet.web.models.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getAllVendors(String filterEmail) {
        List<Vendor> vendors = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)){
            VendorEntity entity = this.vendorRepository.findByEmail(filterEmail);
            vendors.add(this.translateDbToWeb(entity));
        } else {
            Iterable<VendorEntity> entities = this.vendorRepository.findAll();
            entities.forEach(entity-> vendors.add(translateDbToWeb(entity)));
        }
        return vendors;
    }

    public Vendor getVendor (long id) {
        Optional<VendorEntity> vendorEntityOptional = this.vendorRepository.findById(id);
        if(vendorEntityOptional.isEmpty()){
            throw new NotFoundException("vendor not found with id");
        }
        return this.translateDbToWeb(vendorEntityOptional.get());
    }

    public Vendor createOrUpdateVendor(Vendor vendor){
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteVendor(long id){
        this.vendorRepository.deleteById(id);
    }

    private VendorEntity translateWebToDb(Vendor vendor){
        VendorEntity entity = new VendorEntity();
        entity.setId(vendor.getVendorId());
        entity.setName(vendor.getName());
        entity.setAddress(vendor.getAddress());
        entity.setPhone(vendor.getPhoneNumber());
        entity.setEmail(vendor.getEmailAddress());

        return entity;
    }

    private Vendor translateDbToWeb(VendorEntity entity){
        return new Vendor(entity.getId(), entity.getName(), entity.getContact(), entity.getEmail(),
                entity.getPhone(), entity.getAddress());
    }
}
