package com.example.SpringRestExempel;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {


    private List<Product> products = new ArrayList<>();

    private long maxId = 0;

    public Optional<Product> createProduct(Product product) {

        maxId = maxId + 1;
        product.setId(maxId);

        Optional <Product> productOptional= Optional.of(product);
        products.add(product);

        return productOptional;
    }

    public List<Product> getAllProduct() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        Optional <Product> product = products.stream().filter(p -> p.getId() == id).findFirst();

        return product;
    }

    public Product updateProduct(Product newProduct) {

        getProductById(newProduct.getId()).ifPresent(p -> {
            p.setName(newProduct.getName());
            p.setPrice(newProduct.getPrice());
            p.setQuantity(newProduct.getQuantity());
        });

        return newProduct;
    }

    public void deleteProduct(long id) {
        products.removeIf(p -> p.getId() == id);

    }
}
