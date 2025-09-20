package com.frankmoley.lil.wisdom_pet.web.rest;

import com.frankmoley.lil.wisdom_pet.services.VendorService;
import com.frankmoley.lil.wisdom_pet.web.errors.BadRequestException;
import com.frankmoley.lil.wisdom_pet.web.models.Vendor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorRestController {
    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getAllVendors(@RequestParam(name="email", required = false)String email){
        return this.vendorService.getAllVendors(email);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable(name="id")long id){
        return this.vendorService.getVendor(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody Vendor vendor){
        return this.vendorService.createOrUpdateVendor(vendor);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable(name="id")long id, Vendor vendor){
        if (id != vendor.getVendorId()){
            throw new BadRequestException("ids do not match");
        }
        return this.vendorService.createOrUpdateVendor(vendor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable(name="id")long id){
        this.vendorService.deleteVendor(id);
    }
}
