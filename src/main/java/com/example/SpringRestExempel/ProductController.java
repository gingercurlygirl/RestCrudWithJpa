package com.example.SpringRestExempel;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Optional<Product> createProductByRequestBody(@Valid @RequestBody Product product) {

        Optional<Product> result = productService.addProduct(product);

        return result;

    }

    @GetMapping  //localhost:8080/products
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        Optional<Product> product = productService.getProductById(id);
        return product.orElse(null);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product newProduct) throws Exception {

        Optional<Product> op = productService.updateProduct(newProduct);

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
