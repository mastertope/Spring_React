package com.frankmoley.lil.wisdom_pet.web.rest;

import com.frankmoley.lil.wisdom_pet.services.CustomerService;
import com.frankmoley.lil.wisdom_pet.web.errors.BadRequestException;
import com.frankmoley.lil.wisdom_pet.web.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pet_service")
public class PetServiceRestController {
    private final CustomerService customerService;

    public PetServiceRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAll(@RequestParam(name="email", required = false)String email){
        return this.customerService.getAllCustomers(email);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable(name="id")long id){
        return this.customerService.getCustomer(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return this.customerService.createOrUpdateCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(name="id")long id, @RequestBody Customer customer){
        if (id != customer.getCustomerId()){
            throw new BadRequestException("ids do not match");
        }

        return this.customerService.createOrUpdateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable(name="id")long id){
        this.customerService.deleteCustomer(id);
    }
}
