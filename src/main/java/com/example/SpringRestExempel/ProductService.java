package com.example.SpringRestExempel;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository repo;


    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Optional<Product> addProduct (Product product){
        return repo.createProduct(product);
    }

    public List<Product> getAllProducts(){
        return repo.getAllProduct();
    }

    public Optional<Product> getProductById(Long id){
        return repo.getProductById(id);

    }

    public Optional<Product> updateProduct(Product newProduct){

        Optional<Product> product = repo.getProductById(newProduct.getId());
        if (product.isPresent()) {
            repo.updateProduct(newProduct);
            return product;
        }else return product;
    }

    public void deleteProduct(long id){
        repo.deleteProduct(id);

    }
}
