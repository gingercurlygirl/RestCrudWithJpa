package com.example.RestCrudWithJpa;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {

    ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //    @PostMapping("/{id}/{name}/{price}/{quantity}")   //localhost:8080/products
//    public void createProduct(@PathVariable Long id, @PathVariable String name, @PathVariable Double price, @PathVariable Integer quantity) {
//
//        Product p = new Product(id, name, price, quantity);
//        System.out.println(p.toString());
//
//    }
//    @PostMapping
//    public void createProductByQuery(@RequestParam Long id, @RequestParam String name, @RequestParam Double price, @RequestParam Integer quantity) {
//
//        Product p = new Product(id, name, price, quantity);
//        System.out.println(p.toString());
//    }

    @PostMapping    //localhost:8080/products
    public Optional<Channel> createProductByRequestBody(@Valid @RequestBody Channel product) {

        Optional<Channel> result = productService.addProduct(product);

        return result;

    }

    @GetMapping  //localhost:8080/products
    public List<Channel> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Channel getProductById(@PathVariable Long id) {

        Optional<Channel> product = productService.getProductById(id);
        return product.orElse(null);
    }

    @PutMapping
    public ResponseEntity<Channel> updateProduct(@Valid @RequestBody Channel newProduct) throws Exception {

        Optional<Channel> op = productService.updateProduct(newProduct);

        if (op.isPresent()) {
            return ResponseEntity.accepted().body(newProduct);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

//    @GetMapping   //localhost:8080/products
//    public String getAllProducts() {
//        return "";
//
//    }
//    @GetMapping("products/name")   //localhost:8080/products/name
//    public Product getProductByName(String name) {
//
//
//    }
}
