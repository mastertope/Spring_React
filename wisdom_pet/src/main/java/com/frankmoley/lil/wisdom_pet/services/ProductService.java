package com.frankmoley.lil.wisdom_pet.services;

import com.frankmoley.lil.wisdom_pet.data.entities.ProductEntity;
import com.frankmoley.lil.wisdom_pet.data.repositories.ProductRepository;
import com.frankmoley.lil.wisdom_pet.web.errors.NotFoundException;
import com.frankmoley.lil.wisdom_pet.web.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(String name){
        List<Product> products = new ArrayList<>();
        if (StringUtils.hasLength(name)){
            ProductEntity entity = this.productRepository.findByName(name);
            products.add(this.translateDbToWeb(entity));
        } else {
            Iterable<ProductEntity> entities = this.productRepository.findAll();
            entities.forEach(entity-> products.add(this.translateDbToWeb(entity)));
        }
        return products;
    }

    public Product getProduct(long id) {
        Optional<ProductEntity> productEntityOptional = this.productRepository.findById(id);
        if (productEntityOptional.isEmpty()){
            throw new NotFoundException("product not found with id");
        }
        return translateDbToWeb(productEntityOptional.get());
    }

    public Product createOrUpdateProduct (Product product) {
        ProductEntity entity = this.translateWebToDb(product);
        entity = this.productRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteProduct (long id) {
        this.productRepository.deleteById(id);
    }

    private ProductEntity translateWebToDb(Product product){
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setVendorId(product.getVendorId());

        return entity;
    }

    private Product translateDbToWeb (ProductEntity entity){
        return new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getVendorId());
    }
}
