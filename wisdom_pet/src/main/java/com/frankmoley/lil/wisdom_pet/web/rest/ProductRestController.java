package com.frankmoley.lil.wisdom_pet.web.rest;

import com.frankmoley.lil.wisdom_pet.services.ProductService;
import com.frankmoley.lil.wisdom_pet.web.errors.BadRequestException;
import com.frankmoley.lil.wisdom_pet.web.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name="name", required = false) String name){
        return this.productService.getAllProducts(name);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(name="id")long id){
        return this.productService.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return this.productService.createOrUpdateProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name="id")long id, Product product){
        if (id != product.getProductId()){
            throw new BadRequestException("ids do not match");
        }
        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteProduct(@PathVariable(name="id")long id){
        this.productService.deleteProduct(id);
    }
}
